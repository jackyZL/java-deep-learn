package com.zl.thread_pool.concurrent02;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class MyRejected implements RejectedExecutionHandler{

	
	public MyRejected(){
	}
	
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		// 这里的r就是提交的任务
		System.out.println("自定义处理..");
		System.out.println("当前被拒绝任务为：" + r.toString());
		

	}

}
