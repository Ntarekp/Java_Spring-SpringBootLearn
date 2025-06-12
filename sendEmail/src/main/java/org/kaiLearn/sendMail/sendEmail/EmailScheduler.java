package org.kaiLearn.sendMail.sendEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    @Autowired
    private EmailService emailService;

    // Every 2 seconds
    @Scheduled(fixedRate = 10)
    public void sendHelloEmailTask() {
        emailService.sendHelloEmail();
    }
} 