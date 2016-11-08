package com.zl.conn03;


/**
 * 单例模式：最常见的就是饥饿模式，懒汉模式。一个是直接实例化对象，一个是在需要的时候在实例化。
 *
 * 在多线程模式中，考虑到性能和线程安全问题，我们一般选择下面连个比较经典的单例模式，在性能提高的同时，又保证线程安全：
 * Singletion(静态内部类)、DubbleSingleton（双重check）
 *
 */

public class Singletion {  // 推荐，简单

	// 静态内部类的方式
	private static class InnerSingletion {
		private static Singletion single = new Singletion();
	}
	
	public static Singletion getInstance(){
		return InnerSingletion.single;
	}
	
}
