package com.cmp.javahowto.restclient;

import com.swagger.client.codegen.rest.api.MyControllerGreetingApi;
import com.swagger.client.codegen.rest.invoker.ApiClient;

public class ApiManager {

    private static String apiUrl = "http://localhost:8080";
    private static boolean initialized = false;
    private static ApiClient apiClient = null;
    private static MyControllerGreetingApi myControllerGreetingApi = null;

    private static void init(String... apiUrlArg) {
        String url = apiUrlArg.length == 1 ? apiUrlArg[0] : apiUrl;
        apiClient = new ApiClient().setBasePath(url);
        myControllerGreetingApi = new MyControllerGreetingApi(apiClient);
        initialized = true;
    }

    public static MyControllerGreetingApi getMyControllerGreetingApi(String... apiUrlArg) {
        if (initialized) {
            return myControllerGreetingApi;
        }
        init(apiUrlArg);
        return myControllerGreetingApi;
    }

}
