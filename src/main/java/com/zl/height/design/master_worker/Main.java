package com.zl.height.design.master_worker;

import java.util.Random;

/**
 * Master worker的模式：
 *
 * Master-Worker模式是常用的并行计算模式，它的核心思想是系统由两类进程协作工作：Master进程和Worker进程。Master负责接收和分配任务。
 * Worker负责处理子任务，当各个Worker子进程处理完成以后，会将结果返回给Master，由Master做归纳和总结，其好处是能将一个大任务分解成
 * 若干个小任务，并行执行，从而提高系统的吞吐量。
 */
public class Main {

	public static void main(String[] args) {

		int threadCounts = Runtime.getRuntime().availableProcessors(); //获取当前可用的线程数
		System.out.println(threadCounts);
		Master master = new Master(new Worker(), 20);
		
		Random r = new Random();
		for(int i = 1; i <= 100; i++){
			Task t = new Task();
			t.setId(i);
			t.setPrice(r.nextInt(1000));
			master.submit(t);
		}
		master.execute();
		long start = System.currentTimeMillis();
		
		while(true){
			if(master.isComplete()){
				long end = System.currentTimeMillis() - start;
				int priceResult = master.getResult();
				System.out.println("最终结果：" + priceResult + ", 执行时间：" + end);
				break;
			}
		}
		
	}
}
