package org.kaiProj.iams.industrial_attachment.controller;

import lombok.RequiredArgsConstructor;

import org.kaiProj.iams.industrial_attachment.dto.CompanyDTO;
import org.kaiProj.iams.industrial_attachment.model.Company;
import org.kaiProj.iams.industrial_attachment.model.User;
import org.kaiProj.iams.industrial_attachment.service.CompanyService;
import org.kaiProj.iams.industrial_attachment.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
    private final UserService userService;

    @PostMapping("/profile")
    public ResponseEntity<Company> createProfile(@RequestBody CompanyDTO companyDTO) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Company company = companyService.createCompanyProfile(user, companyDTO);
        return ResponseEntity.ok(company);
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<Company> approveCompany(@PathVariable Long id) {
        Company company = companyService.approveCompany(id);
        return ResponseEntity.ok(company);
    }
}