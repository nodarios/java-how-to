package com.cmp.javahowto.json;

import com.cmp.javahowto.lombok.Person;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Test {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) throws IOException {

        String jsonStrOrig = "{\"id\": 1, \"user\": \"test user\"}";
        logger.info("jsonStrOrig {}", jsonStrOrig);


        ObjectMapper mapper = new ObjectMapper();

        /** de */
        //JavaType jt = mapper.getTypeFactory().constructParametricType(List.class, String.class);
        //List<String> l = mapper.readValue(jsonStrOrig, jt);
        Person person = mapper.readValue(jsonStrOrig, Person.class);
        logger.info("person {}", person.toString());

        /** ser */
        String jsonStrNew = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);
        logger.info("jsonStrNew {}", jsonStrNew);

        /** de */
        JsonNode jsonNode = mapper.readTree(jsonStrNew);
        int id = jsonNode.get("id").asInt();
        System.out.println(id);
        String user = jsonNode.get("user").asText();
        System.out.println(user);

    }

}
