package com.xiemj.javasestudy.thread;

/**
 * 银行账户
 */
public class MoneyAccount {

    //余额
    private int Money = 10000;

    /**
     * 获取余额
     * @return
     */
    public   int getMoney() {
        return Money;
    }

    /**
     * 取款
     * @param money
     */
    public   void draw(int money)
    {
        Money  = Money  - money;
    }
}
