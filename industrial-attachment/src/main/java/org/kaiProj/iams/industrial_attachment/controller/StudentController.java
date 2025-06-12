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
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {
    private final OpportunityRepository opportunityRepository;
    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;

    // List all open opportunities
    @GetMapping("/opportunities")
    public ResponseEntity<List<Opportunity>> getOpportunities() {
        List<Opportunity> opportunities = opportunityRepository.findAll();
        return ResponseEntity.ok(opportunities);
    }

    // Apply for a specific opportunity
    @PostMapping("/apply/{opportunityId}")
    public ResponseEntity<?> applyForOpportunity(@PathVariable Long opportunityId, Authentication authentication, @RequestBody ApplicationRequest request) {
        String username = authentication.getName();
        User student = userRepository.findByUsername(username).orElseThrow();
        Opportunity opportunity = opportunityRepository.findById(opportunityId)
                .orElseThrow(() -> new RuntimeException("Opportunity not found"));

        Application application = Application.builder()
                .student(student)
                .opportunity(opportunity)
                .status("Pending")
                .documentsPath(request.getDocumentsPath())
                .build();

        applicationRepository.save(application);
        return ResponseEntity.ok("Application submitted successfully");
    }

    // View all applications submitted by the student
    @GetMapping("/applications")
    public ResponseEntity<List<Application>> getApplications(Authentication authentication) {
        String username = authentication.getName();
        User student = userRepository.findByUsername(username).orElseThrow();
        List<Application> applications = applicationRepository.findByStudent_Id(student.getId());
        return ResponseEntity.ok(applications);
    }

    public static class ApplicationRequest {
        private String documentsPath;

        public String getDocumentsPath() {
            return documentsPath;
        }
        public void setDocumentsPath(String documentsPath) {
            this.documentsPath = documentsPath;
        }
    }
}
