package com.justplay1994.github.performance.lock;

/**
 * Created by huangzezhou
 * Date: 2020/6/30
 * Time: 14:11
 * 信号量
 **/
public class MySemaphore {

	int num;//可用资源

	public MySemaphore(){
		num = 0;
	}

	public MySemaphore(int num){
		this.num = num;
	}

	public synchronized void acquire() throws InterruptedException{
		while (num <= 0){
			this.wait();
		}
		num--;
	}

	public synchronized void release(){
		num++;
		this.notifyAll();
	}

}
