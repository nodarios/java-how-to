package com.cmp.javakafka.testkafka;

import io.prometheus.client.exporter.HTTPServer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        HTTPServer server = new HTTPServer(1235);

        //Thread sizeThread = new Thread(new TestSize());
        //sizeThread.start();
        Thread rateThread = new Thread(new TestRate());
        rateThread.start();
    }

}
