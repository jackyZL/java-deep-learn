package com.zl.conn05;

import java.util.concurrent.SynchronousQueue;


/**
 * Queue的两大主要实现 CouncurrentLinkedQueue和LinkedBlockingQueue，
 * 前者是高性能的队列，后者是阻塞形式的队列，具体实现Queue还有很多，
 * 例如ArrayBlockingQueue,PriorityBlockingQueue,SynchronousQueue等
 *
 * CouncurrentLinkedQueue 是一个适用于高并发场景下的队列，通过无锁的方式，实现了高并发状态下的高性能
 * 通常ConcurrentLinkedQueue性能好与BlockingQueue. 他是一个基于链接节点的无界线程安全队列。该队列
 * 的元素遵循先进先出的原则。 头是最先加入的元素，尾是最近加入的元素，该队列元素不允许为NULL
 *
 * CouncurrentLinkedQueue的重要方法：
 * ① add()和offer() 都是加入元素，(在CouncurrentLinkedQueue中，这两个方法没有任何区别)
 * ② poll() 和 peek() 都是取头元素，区别在于前者会删除元素，后者不会、
 *
 *
 * BlockingQueue接口的实现：
 * ① ArrayBlockQueue : 基于数组的阻塞队列实现，在ArrayBlockingQueue的内部，维护了一个定长数组，以便缓存队列中的数据对象，其内部没有实现读写分离，也就意味着生产者和消费者不能够
 * 完全的并行，长度是需要定义的，可以指定先进先出或者先进后出，也叫有界队列，在很多场合非常适用。
 *
 * ② LikedBlockingQueue ： 基于链表的阻塞队列，同ArrayBlockingQueue类似，其内部也维持着一个数据缓冲队列（该队列由一个链表构成），LinkedBlockingQueue之所以能够高效的并发
 * 处理数据，是因为内部实现采用分离锁（读写分离两个锁），从而实现生产者和消费者的操作完全并行运行，它是一个无界队列。
 *
 * ③ SynchronousQueue : 一种没有缓冲的队列，生产者生产的数据直接会被消费者获取并消费。
 *
 * ④ PriorityBlockingQueue： 基于优先级的阻塞队列(优先级的判断通过构造函数传入的Compator对象来决定，也就是说传入的对象必须实现Comparable接口)，在实现PriorityBlockingQueue
 * 时，内部控制线程同步的锁采用的是公平锁，它是一个无界队列
 *
 * ⑤ DelayQueue ： 带有延迟时间的Queue，其中的元素只有当其指定的时间到了，才能够从队列中获取该元素。DelayQueue中的元素必须实现Delay接口，DelayQueue是一个没有大小限制的队列，应用
 * 场景很多，比如缓存超时的数据进行移除，任务超时处理，空闲连接的关闭等等。
 *
 *
 *
 *
 * @author  jacky
 *
 */
public class UseQueue {

	public static void main(String[] args) throws Exception {
		
		//高性能无阻塞无界队列：ConcurrentLinkedQueue
		/**
		ConcurrentLinkedQueue<String> q = new ConcurrentLinkedQueue<String>();
		q.offer("a");
		q.offer("b");
		q.offer("c");
		q.offer("d");
		q.add("e");
		
		System.out.println(q.poll());	//a 从头部取出元素，并从队列里删除
		System.out.println(q.size());	//4
		System.out.println(q.peek());	//b
		System.out.println(q.size());	//4
		*/
		
		/** 有界长
		ArrayBlockingQueue<String> array = new ArrayBlockingQueue<String>(5);
		array.put("a");
		array.put("b");
		array.add("c");
		array.add("d");
		array.add("e");
		array.add("f");
		//System.out.println(array.offer("a", 3, TimeUnit.SECONDS)); // 3s之后，添加不成功，返回false
		*/



		
		/**
		//阻塞队列
		LinkedBlockingQueue<String> q = new LinkedBlockingQueue<String>(5); // 如果设置5，就有长度限制，如果不设置就没有长度限制
		q.offer("a");
		q.offer("b");
		q.offer("c");
		q.offer("d");
		q.offer("e");
		q.add("f");
		//System.out.println(q.size());
		
//		for (Iterator iterator = q.iterator(); iterator.hasNext();) {
//			String string = (String) iterator.next();
//			System.out.println(string);
//		}
		
		List<String> list = new ArrayList<String>();
		System.out.println(q.drainTo(list, 3)); // 批量从队列中去三个元素，放到集合中
		System.out.println(list.size());
		for (String string : list) {
			System.out.println(string);
		}
		*/
		
		// SynchronousQueue 有一个线程先调用take方法，一个线程执行add方法，是不会报错。
		// 而且需要take方法先执行，其实add方法不是往队列里添加，而是直接给take
		final SynchronousQueue<String> q = new SynchronousQueue<String>();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println(q.take());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();



		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				q.add("asdasd");
			}
		});
		t2.start();		
	}
}
