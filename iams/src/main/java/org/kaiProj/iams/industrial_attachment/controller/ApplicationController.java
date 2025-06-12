package org.kaiProj.iams.industrial_attachment.controller;

import lombok.RequiredArgsConstructor;
import org.kaiProj.iams.industrial_attachment.dto.ApplicationDTO;
import org.kaiProj.iams.industrial_attachment.model.Application;
import org.kaiProj.iams.industrial_attachment.model.ApplicationStatus;
import org.kaiProj.iams.industrial_attachment.model.Student;
import org.kaiProj.iams.industrial_attachment.model.User;
import org.kaiProj.iams.industrial_attachment.service.ApplicationService;
import org.kaiProj.iams.industrial_attachment.service.StudentService;
import org.kaiProj.iams.industrial_attachment.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;
    private final UserService userService;
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Application> apply(@RequestBody ApplicationDTO applicationDTO) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Student student = studentService.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Student profile not found"));
        Application application = applicationService.apply(student, applicationDTO, user);
        return ResponseEntity.ok(application);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Application> updateStatus(@PathVariable Long id, @RequestBody String status) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Application application = applicationService.updateStatus(id, status, user);
        return ResponseEntity.ok(application);
    }

    @GetMapping("/student")
    public ResponseEntity<List<Application>> getStudentApplications() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Student student = studentService.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Student profile not found"));
        return ResponseEntity.ok(applicationService.getApplicationsByStudent(student.getId()));
    }

    @GetMapping("/opportunity/{opportunityId}")
    public ResponseEntity<List<Application>> getOpportunityApplications(@PathVariable Long opportunityId) {
        return ResponseEntity.ok(applicationService.getApplicationsByOpportunity(opportunityId));
    }
}