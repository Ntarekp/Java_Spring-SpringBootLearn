package org.kaiProj.iams.industrial_attachment.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable=false)
    private String username;

    @Column(unique = true, nullable=false)
    private String email;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false)
    private String role; // STUDENT, COMPANY, COORDINATOR, ADMIN

    // Common additional attributes
    private String fullName;

    // STUDENT fields
    private String course;
    private String year;
    private String skills;
    private String cvPath;  // location of the CV document

    // COMPANY fields
    private String companyName;
    private String companyDescription;
    private Boolean approved; // whether admin has approved the company
}
