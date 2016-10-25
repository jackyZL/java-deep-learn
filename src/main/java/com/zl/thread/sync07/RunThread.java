package com.zl.thread.sync07;

/**
 *
 *  分析： 在java中，每一个线程都会有一块内存工作区(自己独有的)，其中存放着所有线程共享的主内存中变量值的拷贝（副本）。 在线程执行过程中
 *  他在自己的内存工作区中操作这些变量。
 *
 *  volatile的作用就是强制线程到主内存（共享内存）中去读取变量的值，而不是去线程工作区去读取，从而实现多个线程间变量可见，
 *  也就满足线程安全的可见性
 *
 *
 */
public class RunThread extends Thread{

	/** volatile **/
	private boolean isRunning = true;
	private void setRunning(boolean isRunning){
		this.isRunning = isRunning;
	}
	
	public void run(){
		System.out.println("进入run方法..");
		int i = 0;
		while(isRunning == true){
			//..
		}
		System.out.println("线程停止");
	}
	
	public static void main(String[] args) throws InterruptedException {
		RunThread rt = new RunThread();
		rt.start();
		Thread.sleep(1000);
		rt.setRunning(false); // 如果isRunning没有被定义成volatile，这里只是修改主线程中的isRunning，并没有修改rt线程中的副本
		System.out.println("isRunning的值已经被设置了false");
	}
	
	
}
