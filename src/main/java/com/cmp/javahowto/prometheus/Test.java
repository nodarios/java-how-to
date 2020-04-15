package com.cmp.javahowto.prometheus;

import io.prometheus.client.Counter;
import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;
import io.prometheus.client.exporter.HTTPServer;

import java.io.IOException;
import java.util.Random;

public class Test {

    private static Counter loopCounter = Counter
            .build()
            .name("loopCounter")
            .help("help")
            .labelNames("topic")
            .register();
    private static Summary loopTimer = Summary
            .build()
            .name("loopTimer")
            .help("help")
            .register();
    private static Summary loopTimer2 = Summary
            .build()
            .name("loopTimer2")
            .help("help")
            .labelNames("labelName")
            .register();
    private static Histogram randomNumberCollector = Histogram
            .build()
            .name("randomNumberCollector")
            .help("help")
            .labelNames("labelName")
            .buckets(10, 100, 1000)
            .register();

    private static long timePrev = 0L;

    public static void main(String[] args) throws IOException, InterruptedException {
        HTTPServer server = new HTTPServer(1235);
        for (int i = 1; i <= 1000; i++) {
            Summary.Timer timer = loopTimer.startTimer();
            timePrev = System.currentTimeMillis();

            Thread.sleep(5000);
            int rand = new Random().nextInt(1000);
            System.out.printf("loop %d, random %d%n", i, rand);

            loopCounter.labels("labelValue").inc();
            timer.observeDuration();
            loopTimer2.labels("labelValue").observe((System.currentTimeMillis() - timePrev) / 1000L);
            randomNumberCollector.labels("labelValue").observe(rand);
        }
        server.stop();
    }

}
