// File: src/main/java/org/greenbasket/controller/OrderController.java
package org.greenbasket.controller;

import org.greenbasket.dto.OrderDto;
import org.greenbasket.entity.Order;
import org.greenbasket.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto) {
        Order order = orderService.createOrder(orderDto);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public List<Order> getUserOrders() {
        return orderService.getOrdersForCurrentUser();
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }
}
