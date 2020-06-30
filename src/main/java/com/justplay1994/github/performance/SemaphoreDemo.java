package com.justplay1994.github.performance;

import com.justplay1994.github.performance.lock.MySemaphore;

/**
 * Created by huangzezhou
 * Date: 2020/6/30
 * Time: 15:26
 **/
public class SemaphoreDemo {

	MySemaphore semaphoreA = new MySemaphore(3);
	MySemaphore semaphoreB = new MySemaphore(0);
	MySemaphore semaphoreZ = new MySemaphore(0);

	class ThreadA extends Thread{
		public void run(){

			try {
				Thread.sleep(100);
				semaphoreA.acquire();
				System.out.println("A");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			semaphoreB.release();
		}
	}

	class ThreadB extends Thread{
		public void run(){
			try {
				semaphoreB.acquire();
				System.out.println("B");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			semaphoreZ.release();
		}
	}

	class ThreadZ extends Thread{
		public void run(){
			try {
				semaphoreZ.acquire();
				System.out.println("Z");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			semaphoreA.release();
		}
	}

	public void start(){
		new ThreadA().start();
		new ThreadA().start();
		new ThreadA().start();
		new ThreadB().start();
		new ThreadB().start();
		new ThreadB().start();
		new ThreadZ().start();
		new ThreadZ().start();
		new ThreadZ().start();
		new ThreadZ().start();
	}

	public static void main(String[] args) throws InterruptedException {
		new SemaphoreDemo().start();
		System.out.println("finished");
	}
}
