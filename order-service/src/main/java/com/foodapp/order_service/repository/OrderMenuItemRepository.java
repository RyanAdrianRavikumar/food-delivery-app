package com.foodapp.order_service.repository;

import com.foodapp.order_service.entity.OrderMenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMenuItemRepository extends JpaRepository<OrderMenuItem, Integer> {
    List<OrderMenuItem> findByOrderId(int orderId);
}
