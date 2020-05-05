package com.cmp.javahowto.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static com.cmp.javahowto.http.HttpMethod.POST;
import static com.cmp.javahowto.http.HttpMethod.PUT;

/**
 * OkHttp, Java 11 HttpClient
 */
class HttpClient {

    private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);

    private HttpURLConnection conn;

    private void prepareConn(String endpoint, HttpMethod method, Map<String, String> headers) throws IOException {
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
//        if (conn.getResponseCode() != 200)
//            throw new RuntimeException("ResponseCode: " + conn.getResponseCode() + ", Message: " + conn.getResponseMessage());
        logger.info("ResponseCode: " + conn.getResponseCode() + ", Message: " + conn.getResponseMessage());
    }

    private String getData() throws IOException {
        StringBuilder data = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                data.append(line);
            }
        }
        return data.toString();
    }

    private void closeConn() {
        conn.disconnect();
    }

    static String request(String outputData, String endpoint, HttpMethod method, Map<String, String> headers)
            throws IOException {
        HttpClient httpClient = new HttpClient();
        httpClient.prepareConn(endpoint, method, headers);
        if (method == POST || method == PUT) httpClient.sendData(outputData);
        httpClient.processResponse();
        String inputData = httpClient.getData();
        httpClient.closeConn();
        logger.info(inputData);
        return inputData;
    }

}
