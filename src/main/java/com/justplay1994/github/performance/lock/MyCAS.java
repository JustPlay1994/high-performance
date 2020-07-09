package com.justplay1994.github.performance.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by huangzezhou
 * Date: 2020/7/7
 * Time: 19:06
 * synchronized是一种悲观锁，获取失败就wait了
 * CAS是一种乐观锁，失败了，会一直尝试，直到成功为止,步骤如下：
 * 1. 复制原对象
 * 2. 锁住总线，判断当前复制对象，与实际对象是否相等
 * 3. 如果相等，则可以对对象进行操作
 * 4. 如果不相等，重复执行1-2
 *
 * 一般会说，用volatile+CAS，就是无锁模式，性能更加高效。
 **/
public class MyCAS {
	volatile int  a = 0;//不使用缓存，多线程可见。

	public void run() throws NoSuchFieldException, IllegalAccessException {
		Field temp = Unsafe.class.getDeclaredField("theUnsafe");
		temp.setAccessible(true);
		Unsafe unsafe = (Unsafe) temp.get(null);
//		unsafe.compareAndSwapObject(Object var1, long var2, Object var4, Object var5);
		Field field = MyCAS.class.getDeclaredField("a");
		long offset = unsafe.objectFieldOffset
				(field);
		int x = unsafe.getAndAddInt(this, offset, 2);
		int b;
		do {
			b = unsafe.getIntVolatile(this, offset);
		}while (!unsafe.compareAndSwapInt(this, offset, b, b + 1));


		AtomicInteger atomicInteger = new AtomicInteger();
		atomicInteger.getAndIncrement();
	}

	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

		new MyCAS().run();

	}

}
