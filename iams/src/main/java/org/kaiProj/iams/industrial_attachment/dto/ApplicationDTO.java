package org.kaiProj.iams.industrial_attachment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ApplicationDTO {
    @NotNull
    private Long opportunityId;

    private String coverLetterPath;
}