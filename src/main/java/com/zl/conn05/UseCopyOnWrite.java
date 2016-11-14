package com.zl.conn05;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;


/**
 * Copy-on-write 简称COW，是一种用于程序设计中的优化策略
 * JDK里面的COW容器有两种：CopyOnWriteArrayList和 CopyOnWriteArraySet,COW容器非常有用，可以在非常多的并发场景下使用到。
 * 什么是CopyOnWrite容器？
 * CopyOnWrite容器即写时复制的容器。通俗的理解是当我们往一个容器里面添加元素的时候，不直接往当前容器添加，而是先将当前容器进行copy，复制出一个
 * 新容器，然后将的容器里添加元素，添加完成元素之后，再将原容器的引用指向新的容器。这样做的好处是，我们可以对CopyOnWrite容器进行并发的读，而不
 * 需要加锁，应为当前容器不需要添加任何锁。所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器。
 *
 * 比较适合读多写少的情况
 */
public class UseCopyOnWrite {

	public static void main(String[] args) {
		
		CopyOnWriteArrayList<String> cwal = new CopyOnWriteArrayList<String>();
		CopyOnWriteArraySet<String> cwas = new CopyOnWriteArraySet<String>();
		
		
	}
}
