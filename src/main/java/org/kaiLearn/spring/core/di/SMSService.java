package org.kaiLearn.spring.core.di;

public class SMSService implements MessageService {

    @Override
    public void sendMessage(String message){
        System.out.println(message);
    }
}
