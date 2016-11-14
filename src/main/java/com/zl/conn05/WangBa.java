package com.zl.conn05;

import java.util.concurrent.DelayQueue;

public class WangBa implements Runnable {
    
    private DelayQueue<Wangmin> queue = new DelayQueue<Wangmin>();  
    
    public boolean yinye =true;

    /**
     * 模拟上机操作
     * @param name  上机人得名字
     * @param id    id号
     * @param money 交了多少钱
     */
    public void shangji(String name,String id,int money){  
        Wangmin man = new Wangmin(name, id, 1000 * money + System.currentTimeMillis());  // 一块钱1秒钟
        System.out.println("网名"+man.getName()+" 身份证"+man.getId()+"交钱"+money+"块,开始上机...");  
        this.queue.add(man);  
    }  
      
    public void xiaji(Wangmin man){  
        System.out.println("网名"+man.getName()+" 身份证"+man.getId()+"时间到下机...");  
    }  
  
    @Override  
    public void run() {  
        while(yinye){  
            try {  
                Wangmin man = queue.take();  // 等到了延迟时间，才从头取出(移除)
                xiaji(man);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
      
    public static void main(String args[]){  
        try{  
            System.out.println("网吧开始营业");  
            WangBa siyu = new WangBa();  
            Thread shangwang = new Thread(siyu);  
            shangwang.start();  
              
            siyu.shangji("路人甲", "123", 1);  
            siyu.shangji("路人乙", "234", 10);  
            siyu.shangji("路人丙", "345", 5);  
        }  
        catch(Exception e){  
            e.printStackTrace();
        }  
  
    }  
}  