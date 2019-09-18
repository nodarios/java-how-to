package com.cmp.javakafka.testkafka;

import io.prometheus.client.exporter.HTTPServer;

import java.io.IOException;

public class TestKafka {

    public static void main(String[] args) throws IOException {
        HTTPServer server = new HTTPServer(1234);

        //Thread sizeThread = new Thread(new TestSize());
        //sizeThread.start();
        Thread rateThread = new Thread(new TestRate());
        rateThread.start();
    }

}
