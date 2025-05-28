package org.kaiLearn.springBoot.SpringDataJPA.repository;

import org.junit.jupiter.api.Test;
import org.kaiLearn.springBoot.SpringDataJPA.domain_entity_model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ProductRepositoryTest {
@Autowired
     private  ProductRepository productRepository;

   @Test
    void saveMethod(){
       //create product

       Product product = new Product();
       product.setName("Product 1");
       product.setDescription("Product 1 description here");
       product.setSku("100ABC");
       product.setPrice(new BigDecimal(100));
       product.setActive(true);
       product.setImageUrl("product1.png");


       //Save product
       Product savedOject = productRepository.save(product);
       System.out.println(savedOject.getId());
       System.out.println(savedOject.toString());
   }
   @Test
   void updateUsingSaveMethod(){
      //Find or retrieve an entity by id
      Long id = 1L;
      Product product = productRepository.findById(id).get();

      //Update entity information
      product.setName("Updated product 1");
      product.setDescription("Updated product 1 dates");

      // Save updated entity
productRepository.save(product);
   }
}