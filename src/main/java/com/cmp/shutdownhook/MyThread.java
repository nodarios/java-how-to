package com.cmp.shutdownhook;

public class MyThread extends Thread {
	
	private Mediator md;
	
	public MyThread(Mediator md) {
		this.md = md;
	}

	public void run() {
		md.setRun(false);
		System.out.println("hook: set run to false");

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("hook: done");
	}
	
}
