package com.justplay1994.github.performance.lock;

/**
 * Created by huangzezhou
 * Date: 2020/6/30
 * Time: 16:51
 **/
public class MyReentrantLock {

	private int num;

	Thread lockedThread;

	public synchronized void lock(){
		if (lockedThread == null || lockedThread != Thread.currentThread()) {
			while (num > 0) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		lockedThread = Thread.currentThread();
		num++;
	}

	public synchronized void unlock(){
		num--;
		if (num <= 0) {
			num = 0;
			lockedThread = null;
			notifyAll();
		}
	}

}
