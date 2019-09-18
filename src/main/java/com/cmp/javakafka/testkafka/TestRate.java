package com.cmp.javakafka.testkafka;

public class TestRate implements Runnable {

    @Override
    public void run() {
        KafkaStats kafkaStats = new KafkaStats();
        try {
            kafkaStats.calcMessageRate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
