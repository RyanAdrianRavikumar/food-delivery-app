package com.foodapp.order_service.service;

import com.foodapp.order_service.entity.Order;
import com.foodapp.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    public Order placeOrder(Order order){
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrderById(int Id){
        Optional<Order> order = orderRepository.findById(Id);
        if(order.isPresent()){
            return order.get();
        }
        return null;
    }

}
