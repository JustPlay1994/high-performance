package com.justplay1994.github.performance.lock;

/**
 * Created by huangzezhou
 * Date: 2020/6/28
 * Time: 17:09
 * 循环栅栏
 *
 * 每次放获取parties个同伴
 *
 * 两个阶段：
 * 1.等待数量到达parties
 * 2.释放parties个过去
 **/
public class MyCyclicBarrier {

	private int parties = 0;

	private int waitNum = 0;

	private boolean isRelease = false;

	private int releaseNum = 0;

	public MyCyclicBarrier(int parties){
		this.parties = parties;
	}

	public synchronized void await() throws InterruptedException{
		waitNum++;
		while (!isRelease){
			if (waitNum >= parties){
				isRelease = true;
			}else {
				this.wait();
			}
		}
		waitNum--;
		releaseNum++;
		if (releaseNum == parties){
			isRelease = false;
			releaseNum = 0;
		}else {
			notifyAll();
		}
	}

}
