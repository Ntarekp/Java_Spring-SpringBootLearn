// File: src/main/java/org/greenbasket/dto/ProductDto.java
package org.greenbasket.dto;

import java.math.BigDecimal;

public class ProductDto {
    private String name;
    private String description;
    private BigDecimal price;
    private String sustainabilityTags; // For example: "Organic,Vegan"

    // Default constructor
    public ProductDto() {}

    // Parameterized constructor
    public ProductDto(String name, String description, BigDecimal price, String sustainabilityTags) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.sustainabilityTags = sustainabilityTags;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSustainabilityTags() {
        return sustainabilityTags;
    }
    public void setSustainabilityTags(String sustainabilityTags) {
        this.sustainabilityTags = sustainabilityTags;
    }
}
