package com.cmp.javahowto.kafka;

import com.cmp.javahowto.lombok.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class Prod {

    public static void main(String[] args) throws JsonProcessingException {
        Properties properties = new Properties();
        properties.put(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        //properties.put("security.protocol", "SASL_PLAINTEXT");
        //properties.put("sasl.kerberos.service.name", "kafka");

        Person p = new Person(5, "user 5");
        String ps = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(p);
        //String message = "{\"id\": 1, \"user\": \"test user\"}";

        try (KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties)) {
            kafkaProducer.send(new ProducerRecord<>("syslog", String.valueOf(p.getId()), ps));
            //kafkaProducer.send(new ProducerRecord<>("syslog", message));
            System.out.println("done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
