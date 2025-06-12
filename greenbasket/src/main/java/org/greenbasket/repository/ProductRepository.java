// File: src/main/java/com/greenbasket/repository/ProductRepository.java
package org.greenbasket.repository;

import org.greenbasket.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> { }
