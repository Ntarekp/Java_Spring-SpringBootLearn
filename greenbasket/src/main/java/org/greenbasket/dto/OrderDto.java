// File: src/main/java/org/greenbasket/dto/OrderDto.java
package org.greenbasket.dto;

import java.util.List;

public class OrderDto {
    // List of OrderItemDto elements making up the order.
    private List<OrderItemDto> items;

    // Default constructor
    public OrderDto() {}

    // Parameterized constructor
    public OrderDto(List<OrderItemDto> items) {
        this.items = items;
    }

    // Getters and Setters
    public List<OrderItemDto> getItems() {
        return items;
    }
    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }
}
