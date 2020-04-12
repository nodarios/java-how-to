package com.cmp.shutdownhook;

import java.util.concurrent.atomic.AtomicBoolean;

public class Mediator {

	private AtomicBoolean run = new AtomicBoolean(true);

	public boolean getRun() {
		return run.get();
	}

	public void setRun(boolean run) {
		this.run.getAndSet(run);
	}
	
}
