// File: src/main/java/org/greenbasket/service/OrderService.java
package org.greenbasket.service;

import org.greenbasket.dto.OrderDto;
import org.greenbasket.dto.OrderItemDto;
import org.greenbasket.entity.Order;
import org.greenbasket.entity.OrderItem;
import org.greenbasket.entity.User;
import org.greenbasket.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    public Order createOrder(OrderDto orderDto) {
        // For demonstration, the current user is simulated.
        User currentUser = userService.authenticate("currentUsername", "dummyPassword");
        Order order = new Order();
        order.setUser(currentUser);
        order.setOrderDate(new Date());
        order.setStatus(Order.OrderStatus.PENDING);

        List<OrderItem> orderItems = orderDto.getItems().stream().map((OrderItemDto itemDto) -> {
            OrderItem item = new OrderItem();
            item.setProduct(productService.getProductById(itemDto.getProductId()));
            item.setQuantity(itemDto.getQuantity());
            item.setPrice(item.getProduct().getPrice());
            item.setOrder(order);
            return item;
        }).collect(Collectors.toList());
        order.setItems(orderItems);
        return orderRepository.save(order);
    }

    public List<Order> getOrdersForCurrentUser() {
        return orderRepository.findAll(); // In production, filter by user.
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
}
