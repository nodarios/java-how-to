package com.cmp.prometheus;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import io.prometheus.client.Summary;
import io.prometheus.client.exporter.PushGateway;

import java.io.IOException;

public class TestPushgateway {

    private static final CollectorRegistry registry = new CollectorRegistry();
    private static final Gauge DURATION = Gauge.build()
            .name("my_batch_job_duration_seconds")
            .help("Duration of my batch job in seconds.")
            .register(registry);
    private static final Gauge LAST_SUCCESS = Gauge.build()
            .name("my_batch_job_last_success")
            .help("Last time my batch job succeeded, in unixtime.")
            .register(registry);
    private static final Summary SUMMARY = Summary.build()
            .name("summary")
            .help("help")
            .register(registry);

    public static void main(String[] args) throws IOException, InterruptedException {
        Gauge.Timer durationTimer = DURATION.startTimer();
        Summary.Timer summaryTimer = SUMMARY.startTimer();
        try {
            Thread.sleep(500);
            LAST_SUCCESS.setToCurrentTime();
        } finally {
            durationTimer.setDuration();
            summaryTimer.observeDuration();
            PushGateway pg = new PushGateway("127.0.0.1:9091");
            pg.pushAdd(registry, "my_batch_job");
        }
    }

}
