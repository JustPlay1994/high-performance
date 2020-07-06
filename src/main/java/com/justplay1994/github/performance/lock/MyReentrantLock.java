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
		System.out.println(Thread.currentThread().getName()+"start");
		if (lockedThread == null || lockedThread != Thread.currentThread()) {
			while (num > 0) {
				try {
					System.out.println(Thread.currentThread().getName()+"is waiting");
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		lockedThread = Thread.currentThread();
		num++;
		System.out.println(Thread.currentThread().getName()+"end");
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
