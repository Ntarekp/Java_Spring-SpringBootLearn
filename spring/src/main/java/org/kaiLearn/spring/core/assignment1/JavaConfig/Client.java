package org.kaiLearn.spring.core.assignment1.JavaConfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        EmailService emailService = applicationContext.getBean(EmailService.class);
        emailService.sendEmail();
    }
}
