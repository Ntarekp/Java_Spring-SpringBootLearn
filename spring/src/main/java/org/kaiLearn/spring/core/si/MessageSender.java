package org.kaiLearn.spring.core.si;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

      private MessageService messageService;
      private MessageService smsService;


      @Autowired
      public void setMessageService(@Qualifier("emailService") MessageService messageService){
          this.messageService = messageService;
      }

      @Autowired
    public void setSmsService(@Qualifier("smsService") MessageService smsService) {
        this.smsService = smsService;
    }

    public void sendMessage(String message){
        this.messageService.sendMessage(message);
        this.smsService.sendMessage(message);
     }

}
