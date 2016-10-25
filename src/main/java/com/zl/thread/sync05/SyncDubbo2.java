package com.zl.thread.sync05;
/**
 * synchronized的重入
 *
 *
 *
 * @author jacky
 *
 */
public class SyncDubbo2 {

	static class Main {
		public int i = 10;
		public synchronized void operationSup(){
			try {
				i--;
				System.out.println("Main print i = " + i);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	static class Sub extends Main {
		public synchronized void operationSub(){
			try {
				while(i > 0) {
					i--;
					System.out.println("Sub print i = " + i);
					Thread.sleep(100);
					/**
					 * 分析： 具有子父类关系，都有synchronize修饰的方法的时候，内部调用也是线程安全的
					 */
					this.operationSup();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				Sub sub = new Sub();
				sub.operationSub();
			}
		});
		
		t1.start();
	}
	
	
}
