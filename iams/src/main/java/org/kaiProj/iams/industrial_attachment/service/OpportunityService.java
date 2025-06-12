package org.kaiProj.iams.industrial_attachment.service;

import lombok.RequiredArgsConstructor;

import org.kaiProj.iams.industrial_attachment.dto.OpportunityDTO;
import org.kaiProj.iams.industrial_attachment.model.Company;
import org.kaiProj.iams.industrial_attachment.model.Opportunity;
import org.kaiProj.iams.industrial_attachment.repository.OpportunityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpportunityService {
    private final OpportunityRepository opportunityRepository;

    public Opportunity createOpportunity(Company company, OpportunityDTO opportunityDTO) {
        Opportunity opportunity = new Opportunity();
        opportunity.setCompany(company);
        opportunity.setTitle(opportunityDTO.getTitle());
        opportunity.setDescription(opportunityDTO.getDescription());
        opportunity.setDeadline(opportunityDTO.getDeadline());
        opportunity.setSlots(opportunityDTO.getSlots());
        return opportunityRepository.save(opportunity);
    }

    public List<Opportunity> getOpenOpportunities() {
        return opportunityRepository.findByIsOpenTrue();
    }
}