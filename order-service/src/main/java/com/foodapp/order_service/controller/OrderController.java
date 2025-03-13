package com.foodapp.order_service.controller;

import com.foodapp.order_service.entity.Order;
import com.foodapp.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://192.168.1.18:3000")
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public Order placeOrder(@RequestBody Order order){
        return orderService.placeOrder(order);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable int id){
        return orderService.getOrderById(id);
    }

    @GetMapping("/all")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }
}
