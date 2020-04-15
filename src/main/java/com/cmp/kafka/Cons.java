package com.cmp.kafka;

import com.cmp.lombok.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class Cons {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "test-group");
        //properties.put("security.protocol", "SASL_PLAINTEXT");
        //properties.put("sasl.kerberos.service.name", "kafka");
        //properties.put("enable.auto.commit", "false");
        //properties.put("auto.offset.reset", "earliest");
        //properties.put("fetch.max.bytes", "50100100");
        //properties.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, "true");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Arrays.asList("syslog"));

        try {
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(10));
                System.out.println(records.count());
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf(
                            "Offset: %d, Key: %s, Value: %s\n",
                            record.offset(), record.key(), record.value()
                    );
                    Person p = new ObjectMapper().readValue(record.value(), Person.class);
                    p.dump();
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
