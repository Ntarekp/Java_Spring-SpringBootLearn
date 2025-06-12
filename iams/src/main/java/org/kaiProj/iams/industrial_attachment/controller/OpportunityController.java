package org.kaiProj.iams.industrial_attachment.controller;

import lombok.RequiredArgsConstructor;
import org.kaiProj.iams.industrial_attachment.dto.CompanyDTO;
import org.kaiProj.iams.industrial_attachment.dto.OpportunityDTO;
import org.kaiProj.iams.industrial_attachment.model.Company;
import org.kaiProj.iams.industrial_attachment.model.Opportunity;
import org.kaiProj.iams.industrial_attachment.model.User;
import org.kaiProj.iams.industrial_attachment.service.CompanyService;
import org.kaiProj.iams.industrial_attachment.service.OpportunityService;
import org.kaiProj.iams.industrial_attachment.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/opportunities")
@RequiredArgsConstructor
public class OpportunityController {
    private final OpportunityService opportunityService;
    private final UserService userService;
    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<Opportunity> createOpportunity(@RequestBody OpportunityDTO opportunityDTO) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Company company = companyService.createCompanyProfile(user, new CompanyDTO()); // Simplified
        Opportunity opportunity = opportunityService.createOpportunity(company, opportunityDTO);
        return ResponseEntity.ok(opportunity);
    }

    @GetMapping
    public ResponseEntity<List<Opportunity>> getOpenOpportunities() {
        return ResponseEntity.ok(opportunityService.getOpenOpportunities());
    }
}