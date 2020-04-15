package com.cmp.kafka;

import com.cmp.lombok.Person;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class Prod2 {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        properties.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

        Person p = new Person(2, "a user");
        String schemaStr =
                "{\"type\":\"record\"," +
                        "\"name\":\"Person\"," +
                        "\"namespace\": \"com.cmp.javakafkaproducer\"," +
                        "\"fields\":[" +
                        "{\"name\":\"id\",\"type\":\"int\"}," +
                        "{\"name\":\"id2\",\"type\":\"int\",\"default\":0}," +
                        "{\"name\":\"user\",\"type\":\"string\"}" +
                        "]}";
        Schema schema = new Schema.Parser().parse(schemaStr);
        GenericRecord gr = new GenericData.Record(schema);
        gr.put("id", p.getId());
        gr.put("user", p.getUser());
        gr.put("id2", 222);

        try (KafkaProducer<String, GenericRecord> kafkaProducer = new KafkaProducer<>(properties)) {
            kafkaProducer.send(new ProducerRecord<>("syslog", String.valueOf(p.getId()), gr));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
