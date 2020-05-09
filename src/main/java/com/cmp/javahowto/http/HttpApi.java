package com.cmp.javahowto.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static com.cmp.javahowto.http.HttpMethod.POST;
import static com.cmp.javahowto.http.HttpMethod.PUT;

/**
 * OkHttp, Java 11 HttpClient
 */
public class HttpApi {

    private static final Logger logger = LoggerFactory.getLogger(HttpApi.class);

    private HttpURLConnection conn;

    private void prepareConn(String endpoint, HttpMethod method, Map<String, String> headers) throws IOException {
        logger.info("endpoint: {}", endpoint);
        URL url = new URL(endpoint);
        conn = (HttpURLConnection) url.openConnection();
        //conn.setConnectTimeout(5000);
        //conn.setReadTimeout(5000);
        //conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod(method.value());
        conn.setRequestProperty("Authorization", "TOKEN_GOES_HERE");
        conn.setRequestProperty("Accept", "application/json");
        if (method == POST || method == PUT) {
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
        }
        if (headers != null)
            headers.forEach((k, v) -> conn.setRequestProperty(k, v));
    }

    private void sendData(String data) throws IOException {
        try (OutputStream out = conn.getOutputStream()) {
            byte[] body = data.getBytes(StandardCharsets.UTF_8);
            //out.write(body, 0, input.length);
            out.write(body);
            out.flush();
        }
    }

    private void processResponse() throws IOException {
        //if (conn.getResponseCode() != 200)
        //    throw new RuntimeException("ResponseCode: " + conn.getResponseCode() + ", Message: " + conn.getResponseMessage());
        logger.info("response code: {}, response message: {}", conn.getResponseCode(), conn.getResponseMessage());
    }

    private String getData() throws IOException {
        StringBuilder data = new StringBuilder();
        InputStream responseStream;
        boolean isErrorStream = false;
        InputStream errorStream = conn.getErrorStream();
        if (errorStream != null) {
            responseStream = errorStream;
            isErrorStream = true;
        } else {
            responseStream = conn.getInputStream();
        }
        try (BufferedReader in = new BufferedReader(new InputStreamReader(responseStream))) {
            String line;
            while ((line = in.readLine()) != null) {
                data.append(line);
            }
        }
        logger.info("response body: {}", data.toString());
        if (isErrorStream) throw new IOException();
        return data.toString();
    }

    private void closeConn() {
        conn.disconnect();
    }

    public static String request(String outputData, String endpoint, HttpMethod method, Map<String, String> headers)
            throws IOException {
        HttpApi httpApi = new HttpApi();
        httpApi.prepareConn(endpoint, method, headers);
        if (method == POST || method == PUT) httpApi.sendData(outputData);
        httpApi.processResponse();
        String inputData = httpApi.getData();
        httpApi.closeConn();
        return inputData;
    }

}
