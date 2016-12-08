package com.zl.concurrent.util;

import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  
import java.util.concurrent.Semaphore;

/**
 * Semaphore信号量非常适合高并发访问，新系统在上线之前，要对系统的访问量进行评估，当然这个值肯定不是随便拍拍脑袋就能想出来的，是经过以往的经验、
 * 数据、历年的访问量，已经推广的力度进行一个合理的评估，当然评估标准不能太大也不能太小，太大的话投入的资源达不到实际效果，纯粹浪费资源，太小
 * 的话，某一个时间点一个高峰值的访问量上来直接可以压垮系统。
 * 相关概念：
 *  ① pv(page view) 网站的总访问量，页面浏览量或者点击量，用户刷新一次就会被记录一次
 *  ② uv(unique vistor) 访问网站的一台电脑客户端为一个访客，一般来讲，时间上以00:00 - 24:00 之内相同的ip的客户端只记录一次
 *  ③ QPS(query per second)即每秒查询数，qps很大程度上代表了系统业务上的繁忙程度，每次请求的背后，可能应对着多次磁盘的I/O 多次网络请求，多个
 *  ④ cpu时间片等。我们通过qps可以非常直观的了解当前系统业务情况，一旦当前qps超过锁设定的预警阈值，可以参考增加机器对集群扩容，以免压力过大导致宕机。
 *  可以根据当前的压力值测试到评估，在结合后期综合运维情况，估算出阈值
 *  ⑤ RT(response time) 请求响应时间，这个指标非常关键，直接索面前端用户体验，因此任何系统设计师都要降低时间
 *
 *  峰值QPS = （总PV * 80%） / (60 * 60 * 24 * 20%)  一般采用的82原则
 *
 *  Semaphore信号量可以用于限流，一般redis用的也比较多，用于限流
 */
public class UseSemaphore {  
  
    public static void main(String[] args) {  
        // 线程池  
        ExecutorService exec = Executors.newCachedThreadPool();  
        // 只能5个线程同时访问  
        final Semaphore semp = new Semaphore(5);  
        // 模拟20个客户端访问  
        for (int index = 0; index < 20; index++) {  
            final int NO = index;  
            Runnable run = new Runnable() {  
                public void run() {  
                    try {
                        //System.out.println(NO + "准备获取许可");
                        // 获取许可，获取不到进行阻塞等待
                        semp.acquire();  // semp的构造函数传入的参数是5，表示可以获取5次，
                        System.out.println("Accessing: " + NO);  
                        //模拟实际业务逻辑
                        Thread.sleep((long) (Math.random() * 10000));
                        // 访问完后，释放许可
                        semp.release();
                    } catch (InterruptedException e) {  
                    }  
                }  
            };  
            exec.execute(run);  
        } 
        
        try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        //System.out.println(semp.getQueueLength());
        
        
        
        // 退出线程池  
        exec.shutdown();  
    }  
  
}  