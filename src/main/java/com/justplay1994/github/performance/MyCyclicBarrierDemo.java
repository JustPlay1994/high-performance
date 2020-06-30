package com.justplay1994.github.performance;

import com.justplay1994.github.performance.lock.MyCyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by huangzezhou
 * Date: 2020/6/28
 * Time: 17:27
 * 循环栅栏 案例：多线程计算数据，最后合并计算结果的场景。
 * CountDownLatch 是一次性的，CyclicBarrier 是可循环利用的
 * CountDownLatch 参与的线程的职责是不一样的，有的在倒计时，有的在等待倒计时结束。CyclicBarrier 参与的线程职责是一样的。
 **/
public class MyCyclicBarrierDemo {

	static int num = 5;

	MyCyclicBarrier cyclicBarrier = new MyCyclicBarrier(num);
//	CyclicBarrier cyclicBarrier = new CyclicBarrier(num);

	class MyThread extends Thread{

		public void run(){
//			System.out.println("getNumberWaiting: "+ cyclicBarrier.getNumberWaiting());
//			System.out.println("getParties: "+ cyclicBarrier.getParties());
//			System.out.println("isBroken: "+ cyclicBarrier.isBroken());
			System.out.println(Thread.currentThread().getName() + "-start");
			try {
				cyclicBarrier.await();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "-end");
		}
	}

	public void start(){
		for (int i = 0; i < num + 3; i++){
			new MyThread().start();
		}
		try {
			System.out.println(Thread.currentThread().getName() + "-start");
			cyclicBarrier.await();
			System.out.println(Thread.currentThread().getName() + "-end1");
			cyclicBarrier.await();
			System.out.println(Thread.currentThread().getName() + "-end");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args){
		new MyCyclicBarrierDemo().start();
		System.out.println("Finished!");
	}
}
