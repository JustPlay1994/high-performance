package com.justplay1994.github.performance.lock;

/**
 * Created by huangzezhou
 * Date: 2020/6/30
 * Time: 17:27
 **/

/**
 * 当前版本：实现多读、多写，读写互斥
 * 坑点一：一个线程通过synchronized嵌套锁住多个对象，然后在最里层调用wait()函数，只释放wait()函数关联的锁对象，而不是释放线程当时持有的全部锁。因此嵌套容易造成持有对象锁永久等待，其他要释放锁的线程进不去
 * 智障点二： 老是只记得lock的时候wait，忘记unlock的时候notify，感觉unlock好像很简单，啥也不用做一样。
 */
public class MyReentrantReadWriteLock {

	private ReadLock readLock = new ReadLock();
	private WriteLock writeLock = new WriteLock();

	private Lock lock = new Lock();

	class Lock{

		private int readNum = 0;
		private int writeNum = 0;

		public final int READ = 0;
		public final int WRITE = 1;

		public synchronized void lock(int status) throws InterruptedException{
			switch (status){
				case READ:
					while (writeNum > 0){
						wait();
					}
					readNum++;
					break;
				case WRITE:
					while (readNum > 0){
						wait();
					}
					writeNum++;
					break;
				default:
					// nothing
					break;
			}
		}

		public synchronized void unlock(int status){
			switch (status){
				case READ:
					readNum--;
					break;
				case WRITE:
					writeNum--;
					break;
				default:
					// nothing
					break;
			}
			notifyAll();
		}
	}

	public class ReadLock{
		public void lock(){
			try {
				lock.lock(lock.READ);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public void unlock(){
			lock.unlock(lock.READ);
		}
	}

	public class WriteLock{
		public void lock(){
			try {
				lock.lock(lock.WRITE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public void unlock(){
			lock.unlock(lock.WRITE);
		}
	}

	public ReadLock readLock(){
		return readLock;
	}

	public WriteLock writeLock(){
		return writeLock;
	}
}
