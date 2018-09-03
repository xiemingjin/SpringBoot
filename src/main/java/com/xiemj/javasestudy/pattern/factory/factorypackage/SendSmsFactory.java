package com.xiemj.javasestudy.pattern.factory.factorypackage;

import com.xiemj.javasestudy.pattern.factory.implementpackage.SmsSender;
import com.xiemj.javasestudy.pattern.factory.interfacepackage.Provider;
import com.xiemj.javasestudy.pattern.factory.interfacepackage.Sender;

public class SendSmsFactory implements Provider {
    @Override
    public Sender produce() {
        return new SmsSender() ;
    }
}
