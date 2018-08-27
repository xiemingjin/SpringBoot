package com.xiemj.javasestudy.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 */
public class ThreadPool {

    private    static   ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(5));

    public static void main(String[] args) {

        ThreadPool test = new ThreadPool();
        //初始化线程池
        //corePoolSize：核心线程数
       // 核心线程会一直存活，及时没有任务需要执行
        //当线程数小于核心线程数时，即使有线程空闲，线程池也会优先创建新线程处理
            //   设置allowCoreThreadTimeout=true（默认false）时，核心线程会超时关闭
       // maxPoolSize：最大线程数
        //当线程数>=corePoolSize，且任务队列已满时。线程池会创建新线程来处理任务
                //当线程数=maxPoolSize，且任务队列已满时，线程池会拒绝处理任务而抛出异常
       // keepAliveTime：线程空闲时间
       // 当线程空闲时间达到keepAliveTime时，线程会退出，直到线程数量=corePoolSize
        //如果allowCoreThreadTimeout=true，则会直到线程数量=0
        List<String> list = new ArrayList<>();
        for(int i = 0 ;i<10000;i++)
        {
            list.add(i+"");
        }
        test.handleList(list, 6);


    }


    public synchronized void handleList(List<String> data, int threadNum) {
        int length = data.size();
        int tl = length % threadNum == 0 ? length / threadNum : (length
                / threadNum + 1);

        for (int i = 0; i < threadNum; i++)
        {
            int end = (i + 1) * tl;
            MyTask myTask = new MyTask("线程[" + (i + 1) + "] ",  data, i * tl, end > length ? length : end);
/*
            executor.execute(myTask);
*/
         Future<List<String>> list =    executor.submit(myTask);
         try {
             System.out.println(list.get());
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }

     /*       for (int a = 0; a < list.size(); a++) {

                System.out.println(threadName + "处理了   " + list.get(a) + "  ！");
                //	System.out.println(threadName+"处理了"+subList.size()+"条！");

            }*/
           /* System.out.println(list.toString());*/
           /* System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    executor.getQueue().size() + "，已执行完别的任务数目：" + executor.getCompletedTaskCount());*/
        }
        executor.shutdown();

    }
}
