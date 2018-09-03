package com.xiemj.javasestudy.pattern.factory.factorypackage;

import com.xiemj.javasestudy.pattern.factory.implementpackage.MailSender;
import com.xiemj.javasestudy.pattern.factory.interfacepackage.Provider;
import com.xiemj.javasestudy.pattern.factory.interfacepackage.Sender;

public class SendMailFactory implements Provider {
    @Override
    public Sender produce() {
        return new MailSender();
    }
}
