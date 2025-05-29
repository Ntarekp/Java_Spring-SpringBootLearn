package org.kaiLearn.springBoot.SpringDataJPA.repository;


import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.kaiLearn.springBoot.SpringDataJPA.domain_entity_model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @Test
    void findByPriceGreaterThanMethod(){
        List<Product> products = productRepository.findByPriceGreaterThan(new BigDecimal(10));
        products.forEach((p)->{
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }
    @Test
    void findByPriceLessThanMethod(){
        List<Product> products = productRepository.
                findByPriceLessThan(new BigDecimal(500));
        products.forEach((p)->{
            System.out.print(p.getId());
            System.out.print(p.getName());
        });
    }
    @Test
    void findByNameContainingMethod(){
        List<Product> products = productRepository.findByNameContaining("product");
        products.forEach((p)->{
            System.out.print(p.getId());
            System.out.print(p.getName());
        });
    }
    @Test
    void findByNameLikeMethod(){
        List<Product> products = productRepository.findByNameLike("product 1 ");
        products.forEach((p)->{
            System.out.println(p.getName());
            System.out.print(p.getDescription());
        });
    }
    @Test
    void findByPriceBetweenMethod(){
        List<Product> products = productRepository.findByPriceBetween(new BigDecimal(0), new BigDecimal(500));
        products.forEach((p)->{
            System.out.println(p.getName());
            System.out.print(p.getDescription());
        });
    }

    @Test
    void findByNameInMethod() {
        List<Product> products = productRepository.findByNameIn(List.of("product 1", "product 2", "product 3"));
        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }
    @Test
    void findByDateCreatedBetweenMethod(){
        LocalDateTime startDate = LocalDateTime.of(2025, 05, 29,17,50,33);
        LocalDateTime endDate = LocalDateTime.of(2025, 05, 29,20, 50,33);
        List<Product> products = productRepository.findByDateCreatedBetween(startDate,endDate);
        products.forEach((p)-> {
            System.out.println(p.getName());
            System.out.print(p.getDescription());

        });
    }
}
