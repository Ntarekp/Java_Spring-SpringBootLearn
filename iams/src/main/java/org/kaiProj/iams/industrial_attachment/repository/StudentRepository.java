package org.kaiProj.iams.industrial_attachment.repository;

import org.kaiProj.iams.industrial_attachment.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByUserId(Long userId);
}