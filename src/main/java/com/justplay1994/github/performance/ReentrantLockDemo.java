package com.justplay1994.github.performance;

import com.justplay1994.github.performance.lock.MyReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by huangzezhou
 * Date: 2020/6/30
 * Time: 16:46
 **/
public class ReentrantLockDemo {

//	ReentrantLock lock = new ReentrantLock();
	MyReentrantLock lock = new MyReentrantLock();

	class MyThread extends Thread{
		public void run(){
			lock.unlock();
			System.out.println(currentThread().getName()+"-start");
			lock.lock();
			System.out.println(currentThread().getName()+"-get");
			lock.lock();
			System.out.println(currentThread().getName()+"-getAgain");
			lock.unlock();
			System.out.println(currentThread().getName()+"-release");
			lock.unlock();
			System.out.println(currentThread().getName()+"-releaseAgain");
		}
	}

	public void start(){
		new MyThread().start();
		new MyThread().start();
	}

	public static void main(String[] args){
		new ReentrantLockDemo().start();
	}
}
