package com.cmp.javakafka;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;

import static io.confluent.kafka.serializers.KafkaAvroDeserializerConfig.*;

import org.apache.avro.generic.GenericRecord;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.util.*;
import java.util.stream.Collectors;

public class TestMessageRate {

    public static void main(String[] args) throws InterruptedException {
        String topic = "syslog";
        KafkaConsumer kafkaConsumer = new KafkaConsumer<String, GenericRecord>(getProps());
        List<TopicPartition> topicPartitions = new ArrayList();
        List<PartitionInfo> partitionInfos = kafkaConsumer.partitionsFor(topic);
        for (PartitionInfo pi : partitionInfos) {
            topicPartitions.add(new TopicPartition(pi.topic(), pi.partition()));
        }
        kafkaConsumer.assign(topicPartitions);
        Map<TopicPartition, Long> prevEndOffsets = null;
        int loops = 1000;
        int intervalSeconds = 10;
        Long totalSum = 0L;

        for (int i = 0; i < loops; i++) {
            Map<TopicPartition, Long> currEndOffsets = getEndOffsets(kafkaConsumer, topicPartitions);
            if (prevEndOffsets != null) {
                Long messageSum = 0L;
                for (TopicPartition tp : topicPartitions) {
                    messageSum += currEndOffsets.get(tp) - prevEndOffsets.get(tp);
                }
                Long messageRate = messageSum > 0 ? messageSum / intervalSeconds : 0L;
                System.out.println(currEndOffsets);
                System.out.println("rate is " + messageRate + " events per second - loop " + i);
                totalSum += messageSum;
            }
            prevEndOffsets = currEndOffsets;
            Thread.sleep(intervalSeconds * 1000);
        }
        Long totalRate = totalSum > 0 ? totalSum / (intervalSeconds * loops) : 0L;
        System.out.println("total rate is " + totalRate + " events per second");
    }

    private static Map<TopicPartition, Long> getEndOffsets(KafkaConsumer kafkaConsumer, List<TopicPartition> topicPartitions) {
        kafkaConsumer.seekToEnd(topicPartitions);
        return topicPartitions.stream().collect(Collectors.toMap((tp) -> tp, kafkaConsumer::position));
    }

    private static Properties getProps() {
        Properties properties = new Properties();
        properties.put(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        properties.put(GROUP_ID_CONFIG, "test-group");
        properties.put(SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
        properties.put(ENABLE_AUTO_COMMIT_CONFIG, "false");
        properties.put(AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(FETCH_MAX_BYTES_CONFIG, "52428800");
        //properties.put("security.protocol", "SASL_PLAINTEXT");
        //properties.put("sasl.kerberos.service.name", "kafka");
        return properties;
    }

}
