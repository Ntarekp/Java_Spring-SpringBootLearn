package org.kaiProj.iams.industrial_attachment.service;

import lombok.RequiredArgsConstructor;

import org.kaiProj.iams.industrial_attachment.model.AuditLog;
import org.kaiProj.iams.industrial_attachment.model.User;
import org.kaiProj.iams.industrial_attachment.repository.AuditLogRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditLogService {
    private final AuditLogRepository auditLogRepository;

    public void logAction(User user, String action, String entityType, Long entityId) {
        AuditLog auditLog = new AuditLog();
        auditLog.setUser(user);
        auditLog.setAction(action);
        auditLog.setEntityType(entityType);
        auditLog.setEntityId(entityId);
        auditLogRepository.save(auditLog);
    }
}