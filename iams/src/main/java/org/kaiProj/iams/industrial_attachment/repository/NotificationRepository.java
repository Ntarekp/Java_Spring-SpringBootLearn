package org.kaiProj.iams.industrial_attachment.repository;

import org.kaiProj.iams.industrial_attachment.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserIdAndIsReadFalse(Long userId);
}