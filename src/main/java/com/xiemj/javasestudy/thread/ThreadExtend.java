package com.xiemj.javasestudy.thread;

/**
 * 线程
 */
public class ThreadExtend extends Thread{

    @Override
    public void run() {
        System.out.println("当前线程：ThreadExtend"+Thread.currentThread().getName());
    }
}
