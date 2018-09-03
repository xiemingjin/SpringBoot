package com.xiemj.javasestudy.pattern.factory.implementpackage;

import com.xiemj.javasestudy.pattern.factory.interfacepackage.Sender;

public class SmsSender implements Sender {
    @Override
    public void Send() {
        System.out.println("SmsSender类打印！");
    }
}
