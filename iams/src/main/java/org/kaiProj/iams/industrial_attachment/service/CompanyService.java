package org.kaiProj.iams.industrial_attachment.service;

import lombok.RequiredArgsConstructor;

import org.kaiProj.iams.industrial_attachment.dto.CompanyDTO;
import org.kaiProj.iams.industrial_attachment.model.Company;
import org.kaiProj.iams.industrial_attachment.model.User;
import org.kaiProj.iams.industrial_attachment.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public Company createCompanyProfile(User user, CompanyDTO companyDTO) {
        Company company = new Company();
        company.setUser(user);
        company.setName(companyDTO.getName());
        company.setDescription(companyDTO.getDescription());
        return companyRepository.save(company);
    }

    public Company approveCompany(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        company.setApproved(true);
        return companyRepository.save(company);
    }
}