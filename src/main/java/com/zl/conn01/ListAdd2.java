package com.zl.conn01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * wait notfiy 方法，wait释放锁，notfiy不释放锁
 * 同一个对象的wait和notify才有效，而且要配合synchronized关键字使用才行
 *
 * @author jacky
 *
 */
public class ListAdd2 {
	private volatile static List list = new ArrayList();	
	
	public void add(){
		list.add("bjsxt");
	}
	public int size(){
		return list.size();
	}
	
	public static void main(String[] args) {
		
		final ListAdd2 list2 = new ListAdd2();
		
		// 1 实例化出来一个 lock01
		// 当使用wait 和 notify 的时候 ， 一定要配合着synchronized关键字去使用
		//final Object lock01 = new Object();

		// 使用notify/wait的机制有一个问题，就是motify通知不实时，需要执行完之后才释放锁，
		// concurrent包下的工具类，做到实时通知效果。它的使用不需要synchronized关键字，参数1表示需要countDownLatch.countDown()一次
		// 就能唤醒countDownLatch.await();
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					//synchronized (lock01) {
						for(int i = 0; i <10; i++){
							list2.add();
							System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
							Thread.sleep(500);
							if(list2.size() == 5){
								System.out.println("已经发出通知..");
								countDownLatch.countDown();
								//lock01.notify();  // notify并不会释放锁，在本例这个逻辑，需要整个for循环完毕了之后才会去释放锁
							}
						}						
					//}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, "t1");
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				//synchronized (lock01) {
					if(list2.size() != 5){
						try {
							System.out.println("t2进入...");
							//lock01.wait();
							countDownLatch.await();  // 线程等待
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
					throw new RuntimeException();
				//}
			}
		}, "t2");	
		
		t2.start(); // 先启动t2,再启动t1
		t1.start();

		
	}
	
}
