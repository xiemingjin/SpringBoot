package com.xiemj.javasestudy.thread;

/**
 * 创建线程
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("当前线程："+Thread.currentThread().getName());
        ThreadExtend t1 = new ThreadExtend();
        t1.setName("t1");
        t1.start();
        ThreadExtend t2 = new ThreadExtend();
        t2.start();
t2.setName("t2");
        ThreadImpl t3 = new ThreadImpl();
        Thread t4 = new Thread(t3);
        //要启动一个新的线程就必须new一个Thread对象出来
         //这里使用的是Thread(Runnable target) 这构造方法
         t4.start();
    }
}
