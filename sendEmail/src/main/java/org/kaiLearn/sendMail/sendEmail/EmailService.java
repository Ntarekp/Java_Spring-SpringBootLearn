package org.kaiLearn.sendMail.sendEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.mail.recipient}")
    private String recipient;

    @Value("${spring.mail.username}")
    private String from;

    public void sendHelloEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(recipient);
        message.setSubject("Hello from Spring GreenBasketPro - Get the best Green Food products to boast your health");
        message.setText("Hello!");
        mailSender.send(message);
    }
} 