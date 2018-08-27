package com.xiemj.javasestudy.thread;

public class AccountMain {

    public static void main(String[] args) {
        AccountThread ar = new AccountThread(500);
        Thread t1 = new Thread(ar);
        t1.setName("第一个人");
        t1.start();

        Thread t2 = new Thread(ar);
        t2.setName("第二个人");
        t2.start();
        Thread t3 = new Thread(ar);
        t3.setName("第3个人");
        t3.start();
        Thread t4 = new Thread(ar);
        t4.setName("第4个人");
        t4.start();
        Thread t5 = new Thread(ar);
        t5.setName("第5个人");
        t5.start();

    }
}
