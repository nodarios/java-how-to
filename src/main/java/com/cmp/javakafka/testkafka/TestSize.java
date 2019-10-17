package com.cmp.javakafka.testkafka;

public class TestSize implements Runnable {

    @Override
    public void run() {
        KafkaStats kafkaStats = new KafkaStats();
        try {
            kafkaStats.calcMessageSize();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            kafkaStats.closeConsumer();
        }
    }

}
