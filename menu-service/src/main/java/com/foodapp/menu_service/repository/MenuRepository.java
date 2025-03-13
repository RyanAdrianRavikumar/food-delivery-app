package com.foodapp.menu_service.repository;

import com.foodapp.menu_service.entity.MenuItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<MenuItems, Integer> {
    List<MenuItems> findByRestaurantId(int restaurantId);
}
