package com.foodapp.delivery_service.repository;

import com.foodapp.delivery_service.entity.DeliveryDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryDriverRepository extends JpaRepository<DeliveryDriver, Integer> {

    DeliveryDriver findByEmail(String email);
}
