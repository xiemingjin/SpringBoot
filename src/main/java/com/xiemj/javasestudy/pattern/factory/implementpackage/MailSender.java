package com.xiemj.javasestudy.pattern.factory.implementpackage;

import com.xiemj.javasestudy.pattern.factory.interfacepackage.Sender;

public class MailSender implements Sender {


    @Override
    public void Send() {
        System.out.println("MailSender类打印!");
    }
}
