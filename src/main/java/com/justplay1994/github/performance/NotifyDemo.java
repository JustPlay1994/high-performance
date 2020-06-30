package com.justplay1994.github.performance;

/**
 * Created by huangzezhou
 * Date: 2020/6/30
 * Time: 15:56
 * 过早通知
 **/
public class NotifyDemo {

	class Test{
		public void await(){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (this){
				try {
					wait(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		public synchronized void anotify(){
			notify();
		}
	}



	class ThreadA extends Thread{
		Test test;

		public ThreadA(Test test){
			this.test = test;
		}
		public void run(){
			test.await();
		}

	}
	class ThreadB extends Thread{
		Test test;

		public ThreadB(Test test){
			this.test = test;
		}
		public void run(){
			test.anotify();
		}

	}

	public void start(){
		Test test = new Test();
		new ThreadA(test).start();
		new ThreadB(test).start();
	}

	public static void main(String[] args){
		new NotifyDemo().start();
	}

}
