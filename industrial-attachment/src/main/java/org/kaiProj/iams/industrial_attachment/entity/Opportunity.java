package org.kaiProj.iams.industrial_attachment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "opportunities")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Opportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String description;

    private LocalDate deadline;

    private int slots;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private User company; // only users with role COMPANY
}
