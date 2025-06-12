package org.kaiProj.iams.industrial_attachment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CompanyDTO {
    @NotBlank
    private String name;

    private String description;
}