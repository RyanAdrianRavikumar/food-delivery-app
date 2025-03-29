package com.foodapp.order_service.controller;

import com.foodapp.order_service.entity.Order;
import com.foodapp.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://192.168.1.18:3000")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<?> placeOrder(@PathVariable int userId, @RequestBody Order order) {
        try {
            // You can set the userId to the order if needed
            order.setUserId(userId);

            // Attempt to save the order
            Order savedOrder = orderService.placeOrder(order);

            return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            // Catching any exception and returning an error response
            e.printStackTrace();  // Log this using a logger in production
            return new ResponseEntity<>("Error placing order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/orders/{orderId}")
    public Order getOrderById(@PathVariable int orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/orders")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/users/{userId}/orders")
    public List<Order> getOrderByUserId(@PathVariable int userId){
        return orderService.findOrderByUserId(userId);
    }
}
