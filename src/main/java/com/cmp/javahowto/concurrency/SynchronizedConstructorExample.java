package com.cmp.javahowto.concurrency;

public class SynchronizedConstructorExample {

    public static void main(String[] args) {
        Thread thread1 = new Thread(SynchronizedConstructorExample::new);
        Thread thread2 = new Thread(SynchronizedConstructorExample::new);

        thread1.start();
        thread2.start();
    }

    private int sharedResource;

    public SynchronizedConstructorExample() {
        synchronized (SynchronizedConstructorExample.class) {
            sharedResource = computeSharedResourceValue();
        }
        System.out.println("Object initialized with sharedResource = " + sharedResource);
    }

    private int computeSharedResourceValue() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 10;
    }

}
