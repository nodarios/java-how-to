package com.cmp.javahowto.http;

public enum HttpMethod {

    POST("POST"),
    PUT("PUT"),
    GET("GET"),
    DELETE("DELETE");

    private final String value;

    HttpMethod(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

}
