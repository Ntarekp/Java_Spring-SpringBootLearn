package org.kaiProj.iams.industrial_attachment.controller;

import lombok.RequiredArgsConstructor;

import org.kaiProj.iams.industrial_attachment.entity.Application;
import org.kaiProj.iams.industrial_attachment.entity.Opportunity;
import org.kaiProj.iams.industrial_attachment.entity.User;
import org.kaiProj.iams.industrial_attachment.repository.ApplicationRepository;
import org.kaiProj.iams.industrial_attachment.repository.OpportunityRepository;
import org.kaiProj.iams.industrial_attachment.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {
    private final OpportunityRepository opportunityRepository;
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;

    // Create a new opportunity
    @PostMapping("/opportunity")
    public ResponseEntity<?> createOpportunity(@RequestBody OpportunityRequest request, Authentication authentication) {
        String username = authentication.getName();
        User company = userRepository.findByUsername(username).orElseThrow();

        Opportunity opportunity = Opportunity.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .deadline(request.getDeadline())
                .slots(request.getSlots())
                .company(company)
                .build();

        opportunityRepository.save(opportunity);
        return ResponseEntity.ok("Opportunity created successfully");
    }

    // View all applications submitted to a specific opportunity
    @GetMapping("/applications/{opportunityId}")
    public ResponseEntity<List<Application>> getApplications(@PathVariable Long opportunityId, Authentication authentication) {
        String username = authentication.getName();
        User company = userRepository.findByUsername(username).orElseThrow();
        Opportunity opportunity = opportunityRepository.findById(opportunityId)
                .orElseThrow(() -> new RuntimeException("Opportunity not found"));
        if(!opportunity.getCompany().getId().equals(company.getId())) {
            return ResponseEntity.badRequest().body(null);
        }
        List<Application> applications = applicationRepository.findByOpportunity_Id(opportunityId);
        return ResponseEntity.ok(applications);
    }

    // Update the status of an application
    @PostMapping("/applications/{applicationId}/status")
    public ResponseEntity<?> updateApplicationStatus(@PathVariable Long applicationId, @RequestBody StatusUpdateRequest request, Authentication authentication) {
        String username = authentication.getName();
        User company = userRepository.findByUsername(username).orElseThrow();

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        if(!application.getOpportunity().getCompany().getId().equals(company.getId())) {
            return ResponseEntity.badRequest().body("Not authorized");
        }
        application.setStatus(request.getStatus());
        applicationRepository.save(application);
        return ResponseEntity.ok("Application status updated");
    }

    public static class OpportunityRequest {
        private String title;
        private String description;
        private java.time.LocalDate deadline;
        private int slots;

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public java.time.LocalDate getDeadline() { return deadline; }
        public void setDeadline(java.time.LocalDate deadline) { this.deadline = deadline; }
        public int getSlots() { return slots; }
        public void setSlots(int slots) { this.slots = slots; }
    }

    public static class StatusUpdateRequest {
        private String status;
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
}
