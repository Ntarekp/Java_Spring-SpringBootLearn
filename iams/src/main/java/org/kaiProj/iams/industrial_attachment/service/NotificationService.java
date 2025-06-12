package org.kaiProj.iams.industrial_attachment.service;

import lombok.RequiredArgsConstructor;

import org.kaiProj.iams.industrial_attachment.model.Notification;
import org.kaiProj.iams.industrial_attachment.model.User;
import org.kaiProj.iams.industrial_attachment.repository.NotificationRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final JavaMailSender mailSender;

    public void sendNotification(User user, String message) {
        // In-app notification
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);
        notificationRepository.save(notification);

        // Email notification
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("IAMS Notification");
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }
}