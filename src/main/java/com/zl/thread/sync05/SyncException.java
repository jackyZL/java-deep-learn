package com.zl.thread.sync05;
/**
 * synchronized异常
 *
 *  出现异常，自动释放锁
 *
 * @author jacky
 *
 */
public class SyncException {

	private int i = 0;
	public synchronized void operation(){
		while(true){
			try {
				i++;
				Thread.sleep(100);
				System.out.println(Thread.currentThread().getName() + " , i = " + i);
				if(i == 20){
					//Integer.parseInt("a");
					throw new RuntimeException();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		
		final SyncException se = new SyncException();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				se.operation();
			}
		},"t1");
		t1.start();
	}
	
	
}
