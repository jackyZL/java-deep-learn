package com.zl.conn05;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * jdk1.5以后提供了多种并发类的容器来替代同步类的容器，从而改善性能。同步类容器的状态都是串行化的，
 * 他们虽然实现了线程安全，但是严重降低了性能，在多线程环境时，严重降低了应用程序的吞吐量。
 * 并发类容器是专门针对并发设计的，使用ConcurrentHashMap来代替给予散列的传统的HashTable、
 * 而且在ConcurrentHashMap中，添加了一些常见的复合操作支持。以及使用了CopyOnWriteArrayList代替Vector，
 * 并发的CopyOnWriteArraySet，以及并发的Queue，CouncurrentLinkedQueue和LinkedBlockingQueue，
 * 前者是高性能的队列，后者是阻塞形式的队列，具体实现Queue还有很多，
 * 例如ArrayBlockingQueue,PriorityBlockingQueue,SynchronousQueue等
 */

/**
 * ConcurrentMap接口有两个实现：ConcurrentHashMap和ConcurrentSkipListMap（支持并发功能，弥补ConcurrentHashMap）
 * <p>
 * ConcurrentHashMap 内部使用段（Segment）来表示这些不同的部分，每个段其实就是一个小的HashTable，他们有自己的锁。
 * 只要多个修改操作发生在不同的段上，它们就可以并发的进行。把一个整体分成16个段（segment），也就是最高支持16个线程的并发修改操作。
 * 这也是在多线程场景时减小锁粒度从而降低锁竞争的一种方案。并且代码中大多共享变量使用Volatile的声明，目的是第一时间获取修改的内容，
 * 性能非常的好~
 *
 */

public class UseConcurrentMap {

    public static void main(String[] args) {
        ConcurrentHashMap<String, Object> chm = new ConcurrentHashMap<String, Object>();
        chm.put("k1", "v1");
        chm.put("k2", "v2");
        chm.put("k3", "v3");

        // 如果不存在k4 就添加，如果存在就不添加
        chm.putIfAbsent("k4", "vvvv");
        //System.out.println(chm.get("k2"));
        //System.out.println(chm.size());

        for (Map.Entry<String, Object> me : chm.entrySet()) {
            System.out.println("key:" + me.getKey() + ",value:" + me.getValue());
        }


    }
}
