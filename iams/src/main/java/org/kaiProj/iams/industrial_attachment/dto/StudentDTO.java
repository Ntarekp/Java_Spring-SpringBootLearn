package org.kaiProj.iams.industrial_attachment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentDTO {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String course;
    private Integer yearOfStudy;
    private String skills;
    private String cvPath;
}