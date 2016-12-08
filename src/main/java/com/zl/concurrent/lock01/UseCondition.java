package com.zl.concurrent.lock01;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 我们在使用synchronizated的时候，如果需要多线程间进行协调工作则需要Object的wait()和notify()， notifyAll() 方法进行配合工作
 * 那么同样，我们在使用Lock的时候，可以使用一个新的等待/通知的类，它就是Condition。这个Condition一定是针对具体某一把锁的，也就是
 * 在只有锁的基础之上才会产生COndition
 */
public class UseCondition {

	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition(); // 有锁，才有Condition
	
	public void method1(){
		try {
			lock.lock();
			System.out.println("当前线程：" + Thread.currentThread().getName() + "进入等待状态..");
			Thread.sleep(3000);
			System.out.println("当前线程：" + Thread.currentThread().getName() + "释放锁..");
			// 等待，并释放锁
			condition.await();	// Object wait
			System.out.println("当前线程：" + Thread.currentThread().getName() +"继续执行...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void method2(){
		try {
			lock.lock();
			System.out.println("当前线程：" + Thread.currentThread().getName() + "进入..");
			Thread.sleep(3000);
			System.out.println("当前线程：" + Thread.currentThread().getName() + "发出唤醒..");
			// 唤醒操作，不释放锁，等到锁释放之后，condition.await()才能继续执行
			condition.signal();		//Object notify

			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		
		final UseCondition uc = new UseCondition();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				uc.method1();
			}
		}, "t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				uc.method2();
			}
		}, "t2");
		t1.start();

		t2.start();
	}
	
	
	
}
