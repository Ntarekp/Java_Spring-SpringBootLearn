package org.kaiProj.iams.industrial_attachment.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private String course;

    @Column(name = "year_of_study")
    private Integer yearOfStudy;

    private String skills;

    @Column(name = "cv_path")
    private String cvPath;
}