package com.xiemj.javasestudy.pattern.singleton;

import java.lang.reflect.Constructor;

public class EnumSingletonTest {

    public static void main(String[] args) {
        EnumSingleton singleton1=EnumSingleton.INSTANCE;

        EnumSingleton singleton2=EnumSingleton.INSTANCE;
        singleton1.test();
        System.out.println(singleton1);
        System.out.println(singleton2);

        try {
            // 用反射来破坏单例模式   怎么防止呢？？？====》用枚举 看EnumSingleton类
            Constructor<EnumSingleton> constructor=EnumSingleton.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            EnumSingleton singleton3=constructor.newInstance();
            System.out.println(singleton3);
            System.out.println(singleton1==singleton2);
            System.out.println(singleton1==singleton3);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
