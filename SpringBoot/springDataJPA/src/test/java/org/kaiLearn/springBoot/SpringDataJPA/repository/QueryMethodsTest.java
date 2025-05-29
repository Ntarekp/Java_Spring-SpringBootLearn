package org.kaiLearn.springBoot.SpringDataJPA.repository;


import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.kaiLearn.springBoot.SpringDataJPA.domain_entity_model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class QueryMethodsTest {


    @Autowired
    private ProductRepository productRepository;


    @Test
    void findByName(){
        Product product = productRepository.findByName("product 1");
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    @Test
    void findByIdMethod(){
         Product product = productRepository.findById(1L).get();
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }
@Test
    void findByNameOrDescriptionMethod(){
        List<Product> products = productRepository.findByNameOrDescription("product 2", "product 1 description");
        products.forEach((p)->{
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }

    @Test
    void findByNameAndDescriptionMethod(){
        List<Product> products = productRepository.findByNameAndDescription("product 2", "product 2 description");
        products.forEach((p)->{
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }
    @Test
    void findDistinctByNamemethod(){
        Product product = productRepository.findDistinctByName("product 1");
        System.out.println(product.getId());
        System.out.println(product.getName());
    }
}
