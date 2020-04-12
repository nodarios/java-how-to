package com.cmp.shutdownhook;

public class MyRunnable implements Runnable {

    private Mediator md;

    public MyRunnable(Mediator md) {
        this.md = md;
    }

    @Override
    public void run() {
        while (md.getRun()) {
            System.out.println("running");
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("done");
    }

}
