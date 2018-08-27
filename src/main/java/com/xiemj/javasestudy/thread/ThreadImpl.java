package com.xiemj.javasestudy.thread;

public class ThreadImpl implements Runnable {
    @Override
    public void run() {
        System.out.println("当前线程:ThreadImpl"+Thread.currentThread().getName());
    }
}
