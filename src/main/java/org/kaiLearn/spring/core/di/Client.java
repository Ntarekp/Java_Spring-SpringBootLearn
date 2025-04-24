package org.kaiLearn.spring.core.di;

public class Client {

    public static void main(String [] args){

        String message = "Hi and good morning, have a nice day.";
        SMSService smsService = new SMSService();
//        EmailService emailService = new EmailService();
        MessageSender messageSender = new MessageSender(smsService);
        messageSender.sendMessage(message);
    }
}
