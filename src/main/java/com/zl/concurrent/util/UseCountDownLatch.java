package com.zl.concurrent.util;

import java.util.concurrent.CountDownLatch;


/**
 * Concurrent.util下的常用类 CountDownLatch
 *
 * 他经常用于监听某些初始化操作（时间可能很长），等初始化执行完毕后，通知主线程继续工作
 *
 * CountDownLatch和CyclicBarrier的区别：
 * CountDownLatch是一个线程等待，等待其他线程去给它发通知，然后执行
 * CyclicBarrier是所有线程都在等待，当所有线程都执行到barrier.await();的时候，表示线程已经准备好了，然后各自执行各自的线程任务
 *
 */
public class UseCountDownLatch {

	public static void main(String[] args) {

		// 参数2 表示调用countDown.countDown()两次，才能唤醒countDown.await();
		final CountDownLatch countDown = new CountDownLatch(2);
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("进入线程t1" + "等待其他线程处理完成...");
					// 当前线程等待着...
					countDown.await();
					System.out.println("t1线程继续执行...");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"t1");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("t2线程进行初始化操作...");
					Thread.sleep(3000);
					System.out.println("t2线程初始化完毕，通知t1线程继续...");
					countDown.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("t3线程进行初始化操作...");
					Thread.sleep(4000);
					System.out.println("t3线程初始化完毕，通知t1线程继续...");
					countDown.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		t3.start();
		
		
		
	}
}
