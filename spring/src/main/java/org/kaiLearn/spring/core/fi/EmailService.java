package org.kaiLearn.spring.core.fi;

import org.springframework.stereotype.Component;

@Component("emailService")
public class EmailService implements MessageService {


    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }
}
