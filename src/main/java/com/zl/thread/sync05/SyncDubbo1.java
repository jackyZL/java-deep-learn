package com.zl.thread.sync05;
/**
 * synchronized的锁重入
 * @author jacky
 *
 */
public class SyncDubbo1 {

	public synchronized void method1(){
		System.out.println("method1..");
		method2(); // 在method1里面，调用加了synchronize的method2方法，是完全没有问题的
	}
	public synchronized void method2(){
		System.out.println("method2..");
		method3();
	}
	public synchronized void method3(){
		System.out.println("method3..");
	}
	
	public static void main(String[] args) {
		final SyncDubbo1 sd = new SyncDubbo1();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				sd.method1();
			}
		});
		t1.start();
	}
}
