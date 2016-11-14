package com.zl.conn05;

import java.util.concurrent.PriorityBlockingQueue;

public class UsePriorityBlockingQueue {

	
	public static void main(String[] args) throws Exception{
		
		
		PriorityBlockingQueue<Task> q = new PriorityBlockingQueue<Task>();
		
		Task t1 = new Task();
		t1.setId(3);
		t1.setName("id为3");
		Task t2 = new Task();
		t2.setId(4);
		t2.setName("id为4");
		Task t3 = new Task();
		t3.setId(1);
		t3.setName("id为1");
		Task t4 = new Task();
		t4.setId(6);
		t4.setName("id为6");
		
		//return this.id > task.id ? 1 : 0;
		q.add(t1);	//3
		q.add(t2);	//4
		q.add(t3);  //1
		q.add(t4);  //6
		// 不是在add方法的时候进行排序 ,直接打印 队列q System.out.print(q); 是不会出现1,3,4,6

		// 每次调用take方法，会将队列的优先级进行排序，
		System.out.println("容器：" + q);
		System.out.println(q.take().getId());
		System.out.println("容器：" + q);
		System.out.println(q.take().getId());
		System.out.println(q.take().getId());
		System.out.println(q.take().getId());


		
	}
}
