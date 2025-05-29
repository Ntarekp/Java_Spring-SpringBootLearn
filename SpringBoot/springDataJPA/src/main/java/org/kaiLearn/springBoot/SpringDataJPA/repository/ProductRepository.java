package org.kaiLearn.springBoot.SpringDataJPA.repository;

import org.kaiLearn.springBoot.SpringDataJPA.domain_entity_model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * ## ProductRepository Interface
 *
 * This interface serves as the data access layer for the {@link Product} entity.
 * It extends Spring Data JPA's {@link JpaRepository}, providing standard CRUD
 * (Create, Read, Update, Delete) operations and enabling the creation of custom
 * query methods by following Spring Data JPA's naming conventions.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

       /**
        * Retrieves a single product by its exact name.
        *
        * @param name The name of the product to search for.
        * @return The product matching the given name, or {@code null} if no product is found.
        */
       public Product findByName(String name);

       /**
        * Retrieves an {@link Optional} containing a single product by its unique identifier (ID).
        * Using {@link Optional} helps in handling cases where the product might not be found,
        * preventing {@code NullPointerException}s.
        *
        * @param id The unique identifier of the product to search for.
        * @return An {@link Optional} containing the product if found, or an empty {@link Optional} otherwise.
        */
       Optional<Product> findById(Long id);

       /**
        * Retrieves a list of products that match either the given name or the given description.
        * This method performs an "OR" operation between the name and description criteria.
        *
        * @param name The name to search for.
        * @param description The description to search for.
        * @return A list of products matching either the name or description. Returns an empty list if none are found.
        */
       List<Product> findByNameOrDescription(String name, String description);


       /**
        * Retrieves a list of products that match both the given name AND the given description.
        * This method performs an "AND" operation between the name and description criteria.
        *
        * @param name The name to search for.
        * @param description The description to search for.
        * @return A list of products matching both the name and description. Returns an empty list if none are found.
        */
       List<Product> findByNameAndDescription(String name, String description);

       /**
        * Return the distinct product entry whose name is given as a method parameter
        * If no product entry is found, this method returns null.
        * @param name The name to Search for
        */
       Product findDistinctByName(String name);


       /**
        * Retrieves a list of products whose price is greater than the specified price.
        *
        * @param price The minimum price threshold (exclusive) to filter products by.
        * @return A list of products with prices greater than the specified price. Returns an empty list if none are found.
       */
       List<Product> findByPriceGreaterThan(BigDecimal price);

       /**
        * Retrieves a list of products whose price is less than the specified price.
        *
        * @param price The maximum price threshold (exclusive) to filter products by.
        * @return A list of products with prices less than the specified price. Returns an empty list if none are found.
        */
       List<Product> findByPriceLessThan(BigDecimal price);

       /**
        * Retrieves a list of products whose names contain the specified string.
        * This method performs a case-sensitive partial match on the product name.
        *
        * @param name The substring to search for within product names.
        * @return A list of products whose names contain the specified string. Returns an empty list if none are found.
        */
       List<Product> findByNameContaining(String name);

       /**
        * Retrieves a list of products whose names match the pattern specified by the 'name' parameter.
        * The pattern can include SQL wildcard characters (% for any sequence of characters, _ for a single character).
        *
        * @param name The pattern to match against product names (e.g., "prod%" would match all names starting with "prod").
        * @return A list of products whose names match the pattern. Returns an empty list if none are found.
        */
       List<Product> findByNameLike(String name);


       /**
        * Retrieves a list of products whose price falls within the specified range (inclusive).
        *
        * @param startPrice The lower bound of the price range.
        * @param endPrice   The upper bound of the price range.
        * @return A list of products with prices between the specified range (inclusive). Returns an empty list if none are found.
        */
       List<Product> findByPriceBetween(BigDecimal startPrice, BigDecimal endPrice);

       List<Product> findByDateCreatedBetween(LocalDateTime startDate, LocalDateTime endDate);
       List<Product> findNameIn(List<String> names);

}