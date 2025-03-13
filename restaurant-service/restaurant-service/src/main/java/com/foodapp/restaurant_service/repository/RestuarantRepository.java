package com.foodapp.restaurant_service.repository;

import com.foodapp.restaurant_service.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestuarantRepository extends JpaRepository<Restaurant, Integer> {
}
