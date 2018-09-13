package com.xiemj.javasestudy.thread;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * 有返回实现callable 无返回实现runable
 */
public class MyTask implements Callable
{

    private String threadName;
    private List<String> data;
    private int start;
    private int end;

    public MyTask(String threadName, List<String> data, int start, int end) {
        this.threadName = threadName;
        this.data = data;
        this.start = start;
        this.end = end;

    }
/*        @Override
     public void run() {
            //这里处理数据
            List<String> subList = data.subList(start, end);
            for (int a = 0; a < subList.size(); a++) {

                System.out.println(threadName + "处理了   " + subList.get(a) + "  ！");
                //	System.out.println(threadName+"处理了"+subList.size()+"条！");

            }
        }*/

    @Override
    public Object call() throws Exception {
        List<String> subList = data.subList(start, end);

        return subList;
    }
}
