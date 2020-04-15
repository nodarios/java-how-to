package com.cmp.kafka.testkafka;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        Thread sizeThread = new Thread(new TestSize());
        sizeThread.start();
        //Thread rateThread = new Thread(new TestRate());
        //rateThread.start();

        while(true) {
            if (/*!rateThread.isAlive() &&*/ !sizeThread.isAlive()) {
                break;
            }
            Thread.sleep(5000);
        }
    }

}
