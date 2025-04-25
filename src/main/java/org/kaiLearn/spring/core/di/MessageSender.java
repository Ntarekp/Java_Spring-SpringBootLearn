package org.kaiLearn.spring.core.di;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    /*
    *Now Assume we want to use maybe both the emailService and smsService all together, then here we go
    */
    private MessageService smsService;
    /*
    *This method used below can be used in cases like you spacify what you want to use, explicity,
    */
    private MessageService messageService;


    public MessageSender(@Qualifier("emailService") MessageService messageService){
        this.messageService = messageService;
        System.out.println("Statement Given when we explicitly set to use emailService only");
    }


    @Autowired
    public MessageSender(@Qualifier("emailService") MessageService messageService, @Qualifier("smsService") MessageService smsService){
        this.messageService = messageService;
        this.smsService = smsService;
     System.out.println("State when we have ability to use both.");
   }


    public void sendMessage(String message){
        this.messageService.sendMessage(message);
        this.smsService.sendMessage(message);
    }

}
