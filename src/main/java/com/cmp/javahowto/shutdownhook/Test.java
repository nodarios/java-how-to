package com.cmp.javahowto.shutdownhook;

public class Test {

    /**
     * graceful shutdown:
     * normal exit
     * exit with exception
     * Sending an interrupt signal from the OS. For instance, by pressing Ctrl + C or logging off the OS
     * Calling System.exit() from Java code
     * <p>
     * ungraceful shutdown:
     * Sending a kill signal from the OS. For example, by issuing a kill -9 PID
     * Calling Runtime.getRuntime().halt() from Java code
     * The host OS dies unexpectedly, for example, in a power failure or OS panic
     * <p>
     * Shutdown hooks are basically initialized but unstarted threads.
     * When the JVM begins its graceful shutdown process, it will start all registered hooks in an unspecified order.
     * Shutdown hooks are usually a good place for releasing resources or other similar house-keeping tasks.
     * After running all hooks, the JVM will halt.
     */

	public static void main(String[] args) {
		Mediator mediator = new Mediator();
		Runtime.getRuntime().addShutdownHook(new MyThread(mediator));
		new Thread(new MyRunnable(mediator)).start();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

}
