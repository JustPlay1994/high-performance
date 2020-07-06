package com.justplay1994.github.performance;

import com.justplay1994.github.performance.lock.MyReentrantReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by huangzezhou
 * Date: 2020/6/30
 * Time: 17:06
 **/
public class ReentrantReadWriteLockDemo {

//	ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	MyReentrantReadWriteLock lock = new MyReentrantReadWriteLock();

	class ThreadR extends Thread{
		public ThreadR(String name){
			setName(name);
		}
		public void run(){
			System.out.println(currentThread().getName()+"-startRead");
			lock.readLock().lock();
			System.out.println(currentThread().getName()+"-endRead");
			lock.readLock().unlock();
		}
	}

	class ThreadW extends Thread{
		public ThreadW(String name){
			setName(name);
		}
		public void run(){
			System.out.println(currentThread().getName()+"-startWrite");
			lock.writeLock().lock();
			System.out.println(currentThread().getName()+"-endWrite");
			lock.writeLock().unlock();
		}
	}


	public void start(){
		new ThreadW("W-1").start();
		new ThreadR("R-1").start();
		new ThreadR("R-2").start();
		new ThreadR("R-3").start();
		new ThreadR("R-4").start();
		new ThreadR("R-5").start();
		new ThreadW("W-2").start();
		new ThreadW("W-3").start();
	}


	public static void main(String[] args){
		new ReentrantReadWriteLockDemo().start();
	}
}
