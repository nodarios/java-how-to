package com.cmp.javakafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Cons {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "test-group");
        KafkaConsumer kafkaConsumer = new KafkaConsumer(properties);
        List topics = new ArrayList();
        topics.add("syslog");
        kafkaConsumer.subscribe(topics);

        try {
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(10);
                System.out.println(records.count());
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf(
                            "Offset: %d, Key: %s, Value: %s\n",
                            record.offset(), record.key(), record.value()
                    );
                    Person p = new ObjectMapper().readValue(record.value(), Person.class);
                    p.dump();
                }
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            kafkaConsumer.close();
        }
    }

}
