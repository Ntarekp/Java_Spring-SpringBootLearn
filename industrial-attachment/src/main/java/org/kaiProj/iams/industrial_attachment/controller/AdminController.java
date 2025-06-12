package org.kaiProj.iams.industrial_attachment.controller;

import lombok.RequiredArgsConstructor;
import org.kaiProj.iams.industrial_attachment.entity.User;
import org.kaiProj.iams.industrial_attachment.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor 
public class AdminController {
    private final UserRepository userRepository;

    // List all companies pending approval
    @GetMapping("/companies/pending")
    public ResponseEntity<List<User>> getPendingCompanies() {
        List<User> pendingCompanies = userRepository.findAll()
                .stream()
                .filter(u -> "COMPANY".equals(u.getRole()) && (u.getApproved() == null || !u.getApproved()))
                .toList();
        return ResponseEntity.ok(pendingCompanies);
    }

    // Approve a company registration
    @PostMapping("/companies/{companyId}/approve")
    public ResponseEntity<?> approveCompany(@PathVariable Long companyId) {
        User company = userRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        if(!"COMPANY".equals(company.getRole())){
            return ResponseEntity.badRequest().body("User is not a company");
        }
        company.setApproved(true);
        userRepository.save(company);
        return ResponseEntity.ok("Company approved");
    }

    // Get a list of all users in the system
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
}
