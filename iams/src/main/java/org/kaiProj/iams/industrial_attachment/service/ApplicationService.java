package org.kaiProj.iams.industrial_attachment.service;

import lombok.RequiredArgsConstructor;
import org.kaiProj.iams.industrial_attachment.dto.ApplicationDTO;
import org.kaiProj.iams.industrial_attachment.model.Application;
import org.kaiProj.iams.industrial_attachment.model.ApplicationStatus;
import org.kaiProj.iams.industrial_attachment.model.Opportunity;
import org.kaiProj.iams.industrial_attachment.model.Student;
import org.kaiProj.iams.industrial_attachment.model.User;
import org.kaiProj.iams.industrial_attachment.repository.ApplicationRepository;
import org.kaiProj.iams.industrial_attachment.repository.OpportunityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final OpportunityRepository opportunityRepository;
    private final NotificationService notificationService;
    private final AuditLogService auditLogService;

    public Application apply(Student student, ApplicationDTO applicationDTO, User user) {
        Opportunity opportunity = opportunityRepository.findById(applicationDTO.getOpportunityId())
                .orElseThrow(() -> new RuntimeException("Opportunity not found"));
        Application application = new Application();
        application.setStudent(student);
        application.setOpportunity(opportunity);
        application.setCoverLetterPath(applicationDTO.getCoverLetterPath());
        Application savedApplication = applicationRepository.save(application);
        notificationService.sendNotification(student.getUser(), "Application submitted for " + opportunity.getTitle());
        auditLogService.logAction(user, "CREATE", "Application", savedApplication.getId());
        return savedApplication;
    }

    public Application updateStatus(Long applicationId, String status, User user) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus(ApplicationStatus.valueOf(status));
        Application savedApplication = applicationRepository.save(application);
        notificationService.sendNotification(application.getStudent().getUser(), "Application status updated to " + status);
        auditLogService.logAction(user, "UPDATE", "Application", savedApplication.getId());
        return savedApplication;
    }

    public List<Application> getApplicationsByStudent(Long studentId) {
        return applicationRepository.findByStudentId(studentId);
    }

    public List<Application> getApplicationsByOpportunity(Long opportunityId) {
        return applicationRepository.findByOpportunityId(opportunityId);
    }
}