package com.justplay1994.github.performance;

import com.justplay1994.github.performance.lock.MyLock;

/**
 * Created by huangzezhou
 * Date: 2020/6/28
 * Time: 15:00 - 15:51
 *
 * 交替打印 XYZ n次
 **/
public class MultiPrint {

	MyLock x = new MyLock();
	MyLock y = new MyLock();
	MyLock z = new MyLock();

	int x_num = 0;
	int y_num = 0;
	int z_num = 0;


	int max = 10;

	int threadNum = 3;

	MyLock lock = new MyLock();

	MultiPrint(){
		Thread a = new Thread(new ThreadA());
		Thread b = new Thread(new ThreadB());
		Thread c = new Thread(new ThreadC());

		try{
			y.lock();
			z.lock();
			lock.lock();
		}catch (Exception e){
			e.printStackTrace();
		}

		a.start();
		b.start();
		c.start();

		try {
			lock.lock();
		} catch (Exception e){
			e.printStackTrace();
		}
		System.out.println();
		lock.unlock();
	}

	public static void main(String[] args){
		new MultiPrint();
	}

	public void unlock(){
		if (threadNum == 0){
			lock.unlock();
		}
	}

	//单一职责
	class ThreadA implements Runnable{
		@Override
		public void run() {
			while(x_num < max){
				try {
					x.lock();
				}catch (Exception e){
					e.printStackTrace();
				}
				x_num++;
				System.out.print("X");
				y.unlock();
			}
			threadNum--;
			unlock();
		}
	}


	class ThreadB implements Runnable{
		@Override
		public void run() {
			while(y_num < max){
				try {
					y.lock();
				}catch (Exception e){
					e.printStackTrace();
				}
				y_num++;
				System.out.print("Y");
				z.unlock();
			}
			threadNum--;
			unlock();
		}
	}

	class ThreadC implements Runnable{
		@Override
		public void run() {
			while(z_num < max){
				try {
					z.lock();
				}catch (Exception e){
					e.printStackTrace();
				}
				z_num++;
				System.out.print("Z");
				x.unlock();
			}
			threadNum--;
			unlock();
		}
	}



}
