// File: src/main/java/org/greenbasket/dto/OrderItemDto.java
package org.greenbasket.dto;

public class OrderItemDto {
    private Long productId;
    private int quantity;

    // Default constructor
    public OrderItemDto() {}

    // Parameterized constructor
    public OrderItemDto(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
