package com.cmp.javahowto.json;

import com.cmp.javahowto.lombok.Person;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {

        String jsonStrOrig = "{\"id\": 1, \"user\": \"test user\"}";
        System.out.println(jsonStrOrig);

        ObjectMapper mapper = new ObjectMapper();

        /** de */
        //JavaType jt = mapper.getTypeFactory().constructParametricType(List.class, String.class);
        //List<String> l = mapper.readValue(jsonStrOrig, jt);
        Person person = mapper.readValue(jsonStrOrig, Person.class);
        person.dump();

        /** ser */
        String jsonStrNew = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);
        System.out.println(jsonStrNew);

        /** de */
        JsonNode jsonNode = mapper.readTree(jsonStrNew);
        int id = jsonNode.get("id").asInt();
        System.out.println(id);
        String user = jsonNode.get("user").asText();
        System.out.println(user);

    }

}
