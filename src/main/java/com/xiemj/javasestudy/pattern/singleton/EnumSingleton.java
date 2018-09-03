package com.xiemj.javasestudy.pattern.singleton;

/**
 * 枚举单例
 */
public enum    EnumSingleton {
    INSTANCE;
    public EnumSingleton getInstance(){
        return INSTANCE;
    }

    public void test(){
        System.out.println("sssssssssssssssssssss");
    }
}
