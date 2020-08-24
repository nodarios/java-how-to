package com.cmp.javahowto.restclient;

import com.swagger.client.codegen.rest.api.MyControllerGreetingApi;
import com.swagger.client.codegen.rest.invoker.ApiException;
import com.swagger.client.codegen.rest.model.Greeting;

public class Test {

    public static void main(String[] args) throws ApiException {

        MyControllerGreetingApi myControllerGreetingApi = ApiManager.getMyControllerGreetingApi("http://localhost:8080");
        Greeting greeting = myControllerGreetingApi.greetingUsingGET("");
        System.out.println(greeting);

    }

}
