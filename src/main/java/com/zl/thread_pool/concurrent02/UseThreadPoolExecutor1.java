package com.zl.thread_pool.concurrent02;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 若Executors工厂类无法满足我们的需求，可以自己去创建自定义的线程池，其实Executors工厂类里面的创建线程方法其内部实现均是
 * 用了ThreadPoolExecutor这个类，这个类可以自定义线程池。
 *
 * 自定义的线程池使用详细：
 * 这个构造方法对于队列是什么类型的比较关键：
 * ① 在使用有界队列时：若有新的任务需要执行，如果线程池实际线程数小于corePoolSize,则优先创建线程，若大于corePoolSize，则会
 * 将任务加入队列，若队列已满，则在总线程数不大于maximumPoolSize的前提下，创建新的线程，若线程数大于maximumPoolSize，则执行
 * 拒绝策略。或其他自定义方式
 * ② 无界的任务队列时：LinkedBlockingQueue,与有界队列相比，除非系统资源耗尽，否则无界的任务队列不存在任务入队失败的情况。当有新
 * 任务到来，系统的线程数小于corePoolSize时，则新建线程执行任务。当达到corePoolSize后，就不会继续增加。若后续任由新的任务加入，
 * 而又没有空闲的线程资源，则任务直接进入队列等待。若任务创建和处理的速度差异很大，无界队列则会保持快速增长，直到耗尽系统内存。
 * 无界队列的情况就是靠corePoolSize限制瓶颈的 ，maximumPoolSize已经没有多大意义了
 *
 * JDK拒绝策略：
 *  AbortPolicy : 直接抛出异常，但是线程池里的其它任务正常工作（默认策略）
 *  CallerRunsPolicy : 只要线程池未关闭，该策略直接在调用者线程中，运行当前被丢弃的任务
 *  DiscardOldestPolicy: 丢弃最老的请求，尝试再次提交当前任务
 *  DiscardPolicy : 丢弃无法处理的任务，不给于任何处理
 * 如果需要自定义拒绝策略，可以实现RejectedExecutionHandler接口
 */
public class UseThreadPoolExecutor1 {


    public static void main(String[] args) {
        /**
         * 在使用有界队列时，若有新的任务需要执行，如果线程池实际线程数小于corePoolSize，则优先创建线程，
         * 若大于corePoolSize，则会将任务加入队列，
         * 若队列已满，则在总线程数不大于maximumPoolSize的前提下，创建新的线程，
         * 若线程数大于maximumPoolSize，则执行拒绝策略。或其他自定义方式。
         *
         */
        ThreadPoolExecutor pool = new ThreadPoolExecutor( // 自定义线程池
                1,                //coreSize 核心线程数（初始化就有的线程数）
                2,                //MaxSize 最大的线程数
                60,            //60 线程保持激活的时间
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3)            //指定一种队列 （有界队列）
                //new LinkedBlockingQueue<Runnable>()
                , new MyRejected()  // 当队列已满的时候，线程被拒绝执行的回调（自定义的拒绝策略）
                //, new DiscardOldestPolicy() // 使用JDK自带的DiscardOldestPolicy策略
        );

        MyTask mt1 = new MyTask(1, "任务1");
        MyTask mt2 = new MyTask(2, "任务2");
        MyTask mt3 = new MyTask(3, "任务3");
        MyTask mt4 = new MyTask(4, "任务4");
        MyTask mt5 = new MyTask(5, "任务5");
        MyTask mt6 = new MyTask(6, "任务6");

        pool.execute(mt1);
        pool.execute(mt2);
        pool.execute(mt3);
        pool.execute(mt4);
        pool.execute(mt5);
        pool.execute(mt6);

        // 并不是马上关闭线程池，而是等到线程池中的任务都执行完毕才关闭线程池
        pool.shutdown();

    }
}
