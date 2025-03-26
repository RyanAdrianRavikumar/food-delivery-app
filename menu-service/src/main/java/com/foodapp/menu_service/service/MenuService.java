package com.foodapp.menu_service.service;

import com.foodapp.menu_service.entity.MenuItems;
import com.foodapp.menu_service.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
