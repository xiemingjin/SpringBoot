package com.xiemj.javasestudy.pattern.factory;

import com.xiemj.javasestudy.pattern.factory.factorypackage.SendSmsFactory;
import com.xiemj.javasestudy.pattern.factory.interfacepackage.Provider;
import com.xiemj.javasestudy.pattern.factory.interfacepackage.Sender;

/**
 * 工厂模式测试
 */
public class Test {

    public static void main(String[] args) {
        Provider provider = new SendSmsFactory();
        Sender sender = provider.produce();
        sender.Send();
    }
}
