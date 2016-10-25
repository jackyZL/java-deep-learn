package com.zl.thread.sync07;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile关键字不具备synchronized关键字的原子性（同步）
 *
 *
 * volatile关键字虽然拥有多个线程之间的可见，但是不具备同步性（也就是原子性），可以算上是一个轻量级的synchronized，
 * 性能要比synchronized强很多，不会造成阻塞。需要注意：一般volatile用于只针对多个线程之间的可见变量操作，并不能代替synchronized的
 * 同步功能。
 *
 * 如果一定要进行原子性操作，可以使用原子类atomic类的系列对象，支持原子操作。（注意：atomic类只能保证本身方法的原子性，并不能保证多次操作
 * 的原子性）
 *
 * @author jacky
 *
 */
public class VolatileNoAtomic extends Thread{
	//private static volatile int count; // 如果定义成volatile，并没有将count加到10000
	private static AtomicInteger count = new AtomicInteger(0);
	private static void addCount(){
		for (int i = 0; i < 1000; i++) {
			//count++ ;
			count.incrementAndGet(); // ++
		}
		System.out.println(count);
	}
	
	public void run(){
		addCount();
	}
	
	public static void main(String[] args) {
		
		VolatileNoAtomic[] arr = new VolatileNoAtomic[100];
		for (int i = 0; i < 10; i++) {
			arr[i] = new VolatileNoAtomic();
		}
		
		for (int i = 0; i < 10; i++) {
			arr[i].start();
		}
	}
	
	
	
	
}
