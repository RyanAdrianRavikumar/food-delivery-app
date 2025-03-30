package com.foodapp.menu_service.service;

import com.foodapp.menu_service.entity.MenuItems;
import com.foodapp.menu_service.repository.MenuRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<MenuItems> getMenuByRestaurantId(int restaurantId){
        return menuRepository.findByRestaurantId(restaurantId);
    }

    public MenuItems findById(int menuItemId){
        return menuRepository.findById(menuItemId).orElse(null);
    }

    public MenuItems updateMenuItemPrice(int menuItemId, Double newPrice) {
        // Fetch the menu item by ID
        MenuItems menuItem = menuRepository.findById(menuItemId).orElse(null);

        if (menuItem != null) {
            // Update the price
            menuItem.setPrice(newPrice);
            // Save the updated entity
            menuRepository.save(menuItem);
        }

        return menuItem;
    }

    @Transactional
    public void deleteMenuItem(int id) {
        if (menuRepository.existsById(id)) {
            menuRepository.deleteById(id);
        } else {
            throw new RuntimeException("Menu item with ID " + id + " not found");
        }
    }
}