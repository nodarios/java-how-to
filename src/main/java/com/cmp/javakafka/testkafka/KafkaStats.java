package com.cmp.javakafka.testkafka;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.prometheus.client.Counter;
import io.prometheus.client.Histogram;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.util.*;
import java.util.stream.Collectors;

import static io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.*;
import static org.apache.kafka.clients.consumer.ConsumerConfig.FETCH_MAX_BYTES_CONFIG;

public class KafkaStats {

    private static final String TOPIC = "syslog";
    private static final int LOOPS = 1000;
    private static final int INTERVAL_SECONDS = 10;

    private static final Counter RATE_COUNTER = Counter.build()
            .name("RATE_COUNTER").help("help").labelNames("topic").register();
    private static final Histogram RATE_HISTOGRAM = Histogram.build()
            .name("RATE_HISTOGRAM").help("help").labelNames("topic").buckets(1000, 5000, 10000).register();

    private KafkaConsumer kafkaConsumer;
    private List<TopicPartition> topicPartitions;


    public KafkaStats() {
        Random rand = new Random();
        Properties props = getProps();
        props.put(GROUP_ID_CONFIG, "test-group-" + rand.nextInt(1000));
        KafkaConsumer kafkaConsumer = new KafkaConsumer<String, GenericRecord>(props);
        List<TopicPartition> topicPartitions = new ArrayList();
        List<PartitionInfo> partitionInfos = kafkaConsumer.partitionsFor(TOPIC);
        for (PartitionInfo pi : partitionInfos) {
            topicPartitions.add(new TopicPartition(pi.topic(), pi.partition()));
        }
        kafkaConsumer.assign(topicPartitions);
        this.kafkaConsumer = kafkaConsumer;
        this.topicPartitions = topicPartitions;
    }

    public void closeConsumer() {
        //kafkaConsumer.commitAsync();
        kafkaConsumer.close();
    }

    public void calcMessageSize()
            throws InterruptedException {
        for (int i = 0; i < LOOPS; i++) {
            Map<TopicPartition, Long> startOffsets = getStartOffsets();
            Map<TopicPartition, Long> endOffsets = getEndOffsets();
            Long messages = 0L;
            System.out.println("SIZE");
            for (TopicPartition tp : topicPartitions) {
                Long partMessages = endOffsets.get(tp) - startOffsets.get(tp);
                System.out.println(
                        "messages for " + tp
                                + " start " + startOffsets.get(tp)
                                + " end " + endOffsets.get(tp)
                                + " diff " + partMessages
                );
                messages += partMessages;
            }
            System.out.println("size for " + TOPIC + " " + messages);
            System.out.println();
            Thread.sleep(INTERVAL_SECONDS * 1000);
        }
    }

    public void calcMessageRate()
            throws InterruptedException {
        Map<TopicPartition, Long> prevEndOffsets = null;
        Long totalMessages = 0L;
        for (int i = 0; i < LOOPS; i++) {
            Map<TopicPartition, Long> currEndOffsets = getEndOffsets();
            if (prevEndOffsets != null) {
                Long loopMessages = 0L;
                for (TopicPartition tp : topicPartitions) {
                    loopMessages += currEndOffsets.get(tp) - prevEndOffsets.get(tp);
                }
                Long msgRate = loopMessages > 0 ? loopMessages / INTERVAL_SECONDS : 0L;
                System.out.println("RATE");
                System.out.println(currEndOffsets);
                System.out.println("current rate is " + msgRate + " messages per second - loop " + i);
                totalMessages += loopMessages;
                Long avgMsgRate = totalMessages > 0 ? totalMessages / (INTERVAL_SECONDS * i) : 0L;
                System.out.println("average rate is " + avgMsgRate + " messages per second");
                System.out.println();
                //
                RATE_COUNTER.labels(TOPIC).inc();
                RATE_HISTOGRAM.labels(TOPIC).observe(msgRate);
            }
            prevEndOffsets = currEndOffsets;
            Thread.sleep(INTERVAL_SECONDS * 1000);
        }
    }

    private Map<TopicPartition, Long> getEndOffsets() {
        kafkaConsumer.seekToEnd(topicPartitions);
        return topicPartitions.stream().collect(Collectors.toMap((tp) -> tp, kafkaConsumer::position));
    }

    private Map<TopicPartition, Long> getStartOffsets() {
        kafkaConsumer.seekToBeginning(topicPartitions);
        return topicPartitions.stream().collect(Collectors.toMap((tp) -> tp, kafkaConsumer::position));
    }

    private Properties getProps() {
        Properties properties = new Properties();
        properties.put(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        //properties.put(GROUP_ID_CONFIG, "test-group");
        properties.put(SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
        properties.put(ENABLE_AUTO_COMMIT_CONFIG, "false");
        properties.put(AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(FETCH_MAX_BYTES_CONFIG, "52428800");
        //properties.put("security.protocol", "SASL_PLAINTEXT");
        //properties.put("sasl.kerberos.service.name", "kafka");
        return properties;
    }

}
