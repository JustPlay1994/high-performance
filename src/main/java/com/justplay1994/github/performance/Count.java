package com.justplay1994.github.performance;

import com.justplay1994.github.performance.lock.MyCountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * Created by huangzezhou
 * Date: 2020/6/28
 * Time: 16:29
 **/
public class Count {

//	static CountDownLatch count = new CountDownLatch(100);
	static MyCountDownLatch count = new MyCountDownLatch(100);

	class MyThread extends Thread{
		public void run(){
			System.out.print(1);
			count.countDown();
		}
	}

	public void count(){
		for (int i = 0; i < 100; i++){
			new MyThread().start();
		}

	}

	public static void main(String[] args){
		new Count().count();
		try {
			count.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.print("\nfinished\n");
	}

}
