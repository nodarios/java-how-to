package com.cmp.javahowto.kafka;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class Cons2 {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        properties.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group-a");
        KafkaConsumer<String, GenericRecord> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Arrays.asList("syslog"));

        try {
            while (true) {
                ConsumerRecords<String, GenericRecord> records = kafkaConsumer.poll(Duration.ofMillis(10));
                System.out.println(records.count());
                for (ConsumerRecord<String, GenericRecord> record : records) {
                    GenericRecord gr = record.value();
                    Schema schema = gr.getSchema();
                    System.out.printf("%d %s %d\n", gr.get("id"), gr.get("user"), gr.get("id2"));
                }
                Thread.sleep(1500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            kafkaConsumer.close();
        }
    }

}
