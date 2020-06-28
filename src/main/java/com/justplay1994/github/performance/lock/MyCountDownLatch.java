package com.justplay1994.github.performance.lock;

/**
 * Created by huangzezhou
 * Date: 2020/6/28
 * Time: 16:43
 * 数量减少锁存器
 **/
public class MyCountDownLatch {

	private int number;

	public MyCountDownLatch(int number){
		this.number = number;
	}

	public synchronized void countDown(){
		this.number --;
		this.notifyAll();
	}

	public synchronized void await() throws InterruptedException{
		while (number > 0) {
			this.wait();
		}
	}
}
