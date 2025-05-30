package org.kaiLearn.springboot_restful_services.repository;

import org.kaiLearn.springboot_restful_services.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long>{

}
