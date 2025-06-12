package org.kaiProj.iams.industrial_attachment.repository;

import org.kaiProj.iams.industrial_attachment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}