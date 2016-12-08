package com.zl.concurrent.lock01;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多Condition的情况，我们可以通过一个Lock对象产生多个Condition进行多线程间的交互，非常的灵活。可以使得部分需要唤醒的线程
 * 唤醒，其他线程则继续等待通知。
 */
public class UseManyCondition {

	private ReentrantLock lock = new ReentrantLock();
	// 通过一个Lock生成多个Condition
	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();
	
	public void m1(){
		try {
			lock.lock();
			System.out.println("当前线程：" +Thread.currentThread().getName() + "进入方法m1等待..");
			// c1 等待
			c1.await();
			System.out.println("当前线程：" +Thread.currentThread().getName() + "方法m1继续..");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void m2(){
		try {
			lock.lock();
			System.out.println("当前线程：" +Thread.currentThread().getName() + "进入方法m2等待..");
			// c1 等待
			c1.await();
			System.out.println("当前线程：" +Thread.currentThread().getName() + "方法m2继续..");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void m3(){
		try {
			lock.lock();
			System.out.println("当前线程：" +Thread.currentThread().getName() + "进入方法m3等待..");
			//c2  等待
			c2.await();
			System.out.println("当前线程：" +Thread.currentThread().getName() + "方法m3继续..");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void m4(){
		try {
			lock.lock();
			System.out.println("当前线程：" +Thread.currentThread().getName() + "唤醒..");
			// c1 唤醒所有 ,那么m1 和 m2 才能继续执行
			c1.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void m5(){
		try {
			lock.lock();
			System.out.println("当前线程：" +Thread.currentThread().getName() + "唤醒..");
			// c2 唤醒  m3 才能继续执行
			c2.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		
		
		final UseManyCondition umc = new UseManyCondition();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				umc.m1();
			}
		},"t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				umc.m2();
			}
		},"t2");
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				umc.m3();
			}
		},"t3");
		Thread t4 = new Thread(new Runnable() {
			@Override
			public void run() {
				umc.m4();
			}
		},"t4");
		Thread t5 = new Thread(new Runnable() {
			@Override
			public void run() {
				umc.m5();
			}
		},"t5");
		
		t1.start();	// c1
		t2.start();	// c1
		t3.start();	// c2
		

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		t4.start();	// c1
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t5.start();	// c2
		
	}
	
	
	
}
