package org.kaiLearn.spring.core.di;

public class EmailService implements MessageService  {


    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }
}
