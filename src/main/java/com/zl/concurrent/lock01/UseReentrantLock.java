package com.zl.concurrent.lock01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 在java多线程中，我们知道可以使用synchronized关键词来实现线程间的同步互斥工作，那么其实还有一个更优秀的机制去完成这个"同步互斥"
 * 的工作，他就是Lock对象，我们主要学习两种锁：重入锁（ReentrantLock）和读写锁。
 * 它们有比synchronized更为强大的功能，并且有嗅探锁定，多路分支等功能
 *
 *
 * 重入锁ReentrantLock，在需要进行同步的代码部分加上锁。
 * 但是不要忘记最后一定要释放锁定，不然会造成锁永远无法释放，其他线程永远进不来的后果
 *
 * 使用方式和synchronizated很相似
 */
public class UseReentrantLock {
	
	private Lock lock = new ReentrantLock(); // 固定的模板，都需要在finally中释放锁
	
	public void method1(){
		try {
			lock.lock();
			System.out.println("当前线程:" + Thread.currentThread().getName() + "进入method1..");
			Thread.sleep(1000);
			System.out.println("当前线程:" + Thread.currentThread().getName() + "退出method1..");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 释放锁
			lock.unlock();
		}
	}
	
	public void method2(){
		try {
			lock.lock();
			System.out.println("当前线程:" + Thread.currentThread().getName() + "进入method2..");
			Thread.sleep(2000);
			System.out.println("当前线程:" + Thread.currentThread().getName() + "退出method2..");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// 释放锁
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {

		final UseReentrantLock ur = new UseReentrantLock();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				ur.method1();
				ur.method2();
			}
		}, "t1");

		t1.start();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//System.out.println(ur.lock.getQueueLength());
	}
	
	
}
