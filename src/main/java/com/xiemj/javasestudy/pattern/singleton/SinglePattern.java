package com.xiemj.javasestudy.pattern.singleton;

/**
 * 单例模式
 */
public final  class SinglePattern
{

    private static final SinglePattern instance=new SinglePattern();
    private SinglePattern(){}

    public static SinglePattern getInstance(){
        return instance;
    }

}
