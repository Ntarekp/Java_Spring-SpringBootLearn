package org.kaiProj.iams.industrial_attachment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OpportunityDTO {
    @NotBlank
    private String title;

    private String description;

    @NotNull
    private LocalDate deadline;

    @NotNull
    private Integer slots;
}