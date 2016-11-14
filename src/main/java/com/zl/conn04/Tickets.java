package com.zl.conn04;

import java.util.Vector;

/**
 * 多线程使用Vector或者HashTable的示例（简单线程同步问题）
 *
 * 同步类容器，如古老的Vector和HashTable，这些容器的同步功能其实都是JDK的Collections.synchronized**等工厂方法创建实现的。其底层的实现机制无非是用传统的
 * synchronized对每个公用的方法进行同步，使得每次只能有一个线程访问容器的状态。这很明显不能满足我们互联网时代高并发的需求，在保证线程安全的同时，也必须要有足够
 * 好的性能。
 *
 * 同步类容器是线程安全的，但在某些场合下可能需要加锁来保护复合操作。复合操作如：迭代(反复访问元素，遍历容器中所有元素),跳转(根据指定顺序找到当前元素的下一个元素),
 * 以及条件运算. 这些复合操作在多线程并发修改容器时，可能会出现意外的行为，最经典的就是ConcurrentModifyException,原因是当容器迭代的过程中，被并发的修改了内容，
 * 这是由于早期迭代器设计没有考虑并发修改的问题。
 *
 *
 * @author jacky
 */
public class Tickets {

	public static void main(String[] args) {

		//初始化火车票池并添加火车票:避免线程同步可采用Vector替代ArrayList  HashTable替代HashMap
		
		final Vector<String> tickets = new Vector<String>();

		// 将普通容器加锁，实现线程安全~ 如此时生成的新的map，就是线程安全的
		//Map<String, String> map = Collections.synchronizedMap(new HashMap<String, String>());

		for(int i = 1; i<= 1000; i++){
			tickets.add("火车票"+i);
		}

		// 这样运行会报错：java.util.ConcurrentModificationException，早期的迭代器没有考虑并发修改的问题
		//for (Iterator iterator = tickets.iterator(); iterator.hasNext();) {
		//	String string = (String) iterator.next();
		//	tickets.remove(20);
		//}

		// 下面的操作是不会出现ConcurrentModificationException异常的
		for(int i = 1; i <=10; i ++){
			new Thread("线程"+i){
				public void run(){
					while(true){
						if(tickets.isEmpty()) break;
						System.out.println(Thread.currentThread().getName() + "---" + tickets.remove(0));
					}
				}
			}.start();
		}
	}
}
