package com.xiemj.javasestudy.thread;

public class AccountThread implements Runnable {
    private  int money;

    public AccountThread(int money) {
        this.money = money;
    }

    /**
     * 取款的账户
     */
    MoneyAccount account = new MoneyAccount();

    @Override
    public void run() {
        //synchronized 同步  加锁  默认是 open状态 --close()--open
        synchronized(this)
        {
            System.out.println("取钱数"+this.money);
            System.out.println("余额"+account.getMoney());
            if((this.money-account.getMoney())<=0)
            {
                account.draw(money);
                System.out.println("取款成功,当前余额:"+account.getMoney()+"当前线程为:"+Thread.currentThread().getName());
            }else
            {
                System.out.println("取款失败,当前余额:"+account.getMoney()+"当前线程为:"+Thread.currentThread().getName());

            }
        }
    }
}
