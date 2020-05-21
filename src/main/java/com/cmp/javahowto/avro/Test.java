package com.cmp.javahowto.avro;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;

import java.util.Map;

public class Test {

    public static void main(String[] args) {

        /* handling schema */
        String schemaStr =
                "{\n" +
                        "  \"type\": \"record\",\n" +
                        "  \"name\": \"Person\",\n" +
                        "  \"namespace\": \"com.cmp.javakafkaproducer\",\n" +
                        "  \"fields\": [\n" +
                        "    {\n" +
                        "      \"name\": \"id\",\n" +
                        "      \"type\": \"int\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"name\": \"user\",\n" +
                        "      \"type\": [\"string\",\"null\"],\n" +
                        "      \"default\": \"def val\",\n" +
                        "      \"cust_prop\": \"some prop val\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"name\": \"timestampms\",\n" +
                        "      \"type\": {\n" +
                        "        \"type\": \"long\",\n" +
                        "        \"logicalType\": \"timestamp-millis\"\n" +
                        "      },\n" +
                        "      \"cust_prop_for_source_metadata\": {\n" +
                        "        \"origin_type\": \"datetimeAsString\",\n" +
                        "        \"pattern\": \"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'\"\n" +
                        "      }\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}";
        // use avro maven plugin to generate java sources for avro schemas
        // https://github.com/alexholmes/avro-maven
        Schema schema = new Schema.Parser().parse(schemaStr);
        System.out.println(schema.toString(true));
        schema.getFields().forEach(
                f -> {
                    String nam = f.name();
                    Schema sch = f.schema();
                    Object def = f.defaultVal();
                    String propStr = f.getProp("cust_prop");
                    Object propObj = f.getObjectProp("cust_prop_for_source_metadata");
                    System.out.printf("%s, %s, %s, %s, %s%n", nam, sch, def, propStr, propObj);
                    if (propObj != null) {
                        Map<String, String> propMap = (Map<String, String>) propObj;
                        System.out.println(propMap.get("pattern"));
                    }
                }
        );

        /* handling GenericRecord */
        // GenericData.Record's constructor does not use default values of schema
        GenericRecord gr = new GenericData.Record(schema);
        for (Schema.Field field : schema.getFields()) {
            if (field.name().matches("id")) {
                gr.put(field.name(), null);
            }
            if (field.name().matches("user")) {
                gr.put(field.name(), "a user");
            }
            if (field.name().matches("timestampms")) {
                //gr.put(field.name(), System.currentTimeMillis());
            }
        }
        System.out.println(gr.get("id"));
        System.out.println(gr.get("user"));
        System.out.println(gr.get("timestampms"));
        // GenericRecordBuilder API does use default values
        GenericRecord gr2 = new GenericRecordBuilder(schema)
                .set("id", 4)
                .set("timestampms", System.currentTimeMillis())
                .build();
        System.out.println(gr2.get("id"));
        System.out.println(gr2.get("user"));
        System.out.println(gr2.get("timestampms"));

    }

}
