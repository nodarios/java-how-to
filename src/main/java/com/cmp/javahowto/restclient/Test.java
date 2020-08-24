package com.cmp.javahowto.restclient;

import com.swagger.client.codegen.rest.api.MyControllerApi;
import com.swagger.client.codegen.rest.invoker.ApiException;
import com.swagger.client.codegen.rest.model.Greeting;

public class Test {

    public static void main(String[] args) throws ApiException {

        MyControllerApi myControllerApi = ApiManager.getMyControllerApi("http://localhost:8080");
        Greeting greeting = myControllerApi.greetingUsingGET("");
        System.out.println(greeting);

    }

}
