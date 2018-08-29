package com.xiemj.javasestudy.springbootthread.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.Future;


/**
 * 线程执行任务类
 *
 */
@Service
public class AsyncTaskService
{
    // 默认构造方法
    Random random = new Random();


    /**
     * 无返回值
     * 表明是异步方法
     * @param i
     */
     @Async
    public void executeAsyncTask(Integer i)
     {
            System.out.println("执行异步任务：" + i);
     }


    /**
     * 异常调用返回Future
     *
     * @param i
     * @return
     * @throws InterruptedException
     */
     @Async
     public Future<String> asyncInvokeReturnFuture(int i) throws InterruptedException
     {
         System.out.println("input is " + i);
         Thread.sleep(1000 * random.nextInt(i));
         // Future接收返回值，这里是String类型，可以指明其他类型
         Future<String> future = new AsyncResult<String>("success:" + i);
         return future;
     }
}
