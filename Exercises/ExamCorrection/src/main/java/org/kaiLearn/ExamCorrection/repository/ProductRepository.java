package org.kaiLearn.ExamCorrection.repository;

import org.kaiLearn.ExamCorrection.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Derived (Implicit) query methods

    List<Product> findByName(String name);
    List<Product> findByPriceLessThan(double price);
    List<Product> findByQuantityGreaterThan(int quantity);

    //Custom JPQL Queries using @Query
    @Query("SELECT p FROM Product p WHERE p.price> 1000")
    List<Product> findPremiumProducts();
    @Query("SELECT p FROM Product p where p.name like '%Green%'")
    List<Product> findProductsWithNameContainingGreen();

    @Query("SELECT p FROM Product p WHERE p.quantity =0")
    List<Product> findOutOfStockProductss();


    //JPQL with named parameters

    @Query("select p from Product p where p.price > :minPrice")
    List<Product> findProductWithMinPrice(@Param("minPrice") double minPrice);

    @Query("SELECT p FROM Product p WHERE p.name = :productName")
    List<Product> findByExactName(@Param("productName") String name);

    @Query("SELECT p FROM Product p  WHERE p.quantity BETWEEN :min AND :max")
    List<Product> findProductsInQuantiyRange(@Param("min") int min, @Param("max") int max);


}
