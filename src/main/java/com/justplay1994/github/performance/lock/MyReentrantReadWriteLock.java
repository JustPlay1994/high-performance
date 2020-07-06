package com.justplay1994.github.performance.lock;

/**
 * Created by huangzezhou
 * Date: 2020/6/30
 * Time: 17:27
 **/

/**
 * 当前版本，jconsole检测无死锁，但大概率出现永久等待
 * 坑点一：一个线程通过synchronized嵌套锁住多个对象，然后在最里层调用wait()函数，只释放wait()函数关联的锁对象，而不是释放线程当时持有的全部锁。因此嵌套容易造成持有对象锁永久等待，其他要释放锁的线程进不去
 */
public class MyReentrantReadWriteLock {

	private int readNum;
	private int writeNum;

	private ReadLock readLock = new ReadLock();
	private WriteLock writeLock = new WriteLock();

	private MyReentrantLock mutex = new MyReentrantLock();

	public class ReadLock{
		public synchronized void lock(){
			mutex.lock();
			readNum ++;
		}

		public synchronized void unlock(){
			mutex.unlock();
			readNum --;
		}
	}

	public class WriteLock{
		public void lock(){
			mutex.lock();
			writeNum ++;
		}

		public void unlock(){
			mutex.unlock();
			writeNum --;
		}
	}

	public ReadLock readLock(){
		return readLock;
	}

	public WriteLock writeLock(){
		return writeLock;
	}
}
