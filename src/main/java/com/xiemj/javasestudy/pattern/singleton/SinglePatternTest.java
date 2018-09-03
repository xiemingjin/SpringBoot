package com.xiemj.javasestudy.pattern.singleton;

import java.lang.reflect.Constructor;

/***
 * 单例模式测试类
 */
public class SinglePatternTest {

    public static void main(String[] args) {

        SinglePattern singleton1=SinglePattern.getInstance();
        SinglePattern singleton2=SinglePattern.getInstance();
        System.out.println(singleton1);
        System.out.println(singleton2);
            try {
                // 用反射来破坏单例模式   怎么防止呢？？？====》用枚举 看EnumSingleton类
                Constructor<SinglePattern> constructor=SinglePattern.class.getDeclaredConstructor();
                constructor.setAccessible(true);
                SinglePattern singleton3=constructor.newInstance();
                System.out.println(singleton3);
                System.out.println(singleton1==singleton2);
                System.out.println(singleton1==singleton3);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
    }
}
