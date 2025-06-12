package org.kaiProj.iams.industrial_attachment.repository;

import org.kaiProj.iams.industrial_attachment.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}