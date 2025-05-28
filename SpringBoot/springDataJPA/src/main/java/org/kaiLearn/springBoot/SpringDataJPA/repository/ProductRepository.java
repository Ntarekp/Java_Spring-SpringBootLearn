package org.kaiLearn.springBoot.SpringDataJPA.repository;

import org.kaiLearn.springBoot.SpringDataJPA.domain_entity_model.Product;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductRepository extends JpaRepository<Product, Long> {

}
