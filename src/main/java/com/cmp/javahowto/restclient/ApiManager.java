package com.cmp.javahowto.restclient;

import com.swagger.client.codegen.rest.api.MyControllerApi;
import com.swagger.client.codegen.rest.invoker.ApiClient;

public class ApiManager {

    private static String apiUrl = "http://localhost:8080";
    private static boolean initialized = false;
    private static ApiClient apiClient = null;
    private static MyControllerApi myControllerApi = null;

    private static void init(String... apiUrlArg) {
        String url = apiUrlArg.length == 1 ? apiUrlArg[0] : apiUrl;
        apiClient = new ApiClient().setBasePath(url);
        myControllerApi = new MyControllerApi(apiClient);
        initialized = true;
    }

    public static MyControllerApi getMyControllerApi(String... apiUrlArg) {
        if (initialized) {
            return myControllerApi;
        }
        init(apiUrlArg);
        return myControllerApi;
    }

}
