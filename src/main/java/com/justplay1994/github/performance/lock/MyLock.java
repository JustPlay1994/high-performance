package com.justplay1994.github.performance.lock;

public class MyLock {
	private boolean locked = false;

	public MyLock() {

	}

	public final synchronized void lock() throws InterruptedException {
		while (this.locked) {
			this.wait();
		}

		this.locked = true;
	}

	public final synchronized void unlock() {
		this.locked = false;
		this.notifyAll();
	}
}