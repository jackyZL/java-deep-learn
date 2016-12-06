package com.zl.thread_pool.concurrent01;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 为了更好的控制多线程，JDK提供了一套线程框架Executor，帮助开发人员有效的进行线程控制。它们都在java.util.concurrent包中，
 * 是JDK并发包的核心，其中有一个比较重要的类：Executors，它扮演者线程工厂的角色，我们通过Executors可以创建特定功能的线程池
 *
 * Executors创建线程池的方法：
 *
 * 	① new FixedThreadPool()方法，改方法返回一个固定数量的线程池，改方法的线程数始终不变，当有一个任务提交时，若线程池中空闲，
 * 	则立即执行，若没有，则会被暂缓在一个任务队列中等待有空闲的线程去执行
 *
 * 	② new SingelThreadExecutor()方法，创建一个线程池，若空闲则执行，若没有空闲线程则暂缓在任务队列中
 *
 * 	③ new CachedThreadPool()方法，返回一个可根据实际情况调整线程个数的线程池，不限制最大线程数量，若用空闲的线程则执行任务，
 * 	若无任务则不创建线程，并且每一个空闲的线程都会在60秒后自动回收
 *
 * 	④ new ScheduledThreadPool()方法，改方法返回一个SchededExecutorService对象，但该线程池可以执行线程的数量
 *
 */

public class UseExecutors {

	public static void main(String[] args) {
		
		ExecutorService pool = Executors.newScheduledThreadPool(10);
		
		//cache fixed single
		
		
		
	}
}
