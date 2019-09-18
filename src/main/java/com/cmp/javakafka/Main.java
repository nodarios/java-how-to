package com.cmp.javakafka;

import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("aaa\\xwww".replace("\\x", ""));

        Random rand = new Random();
        while (true) {

            System.out.println("asd-" + rand.nextInt(500));
            Thread.sleep(100);
        }

    }

}
