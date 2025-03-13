package com.foodapp.menu_service.controller;

import com.foodapp.menu_service.entity.MenuItems;
import com.foodapp.menu_service.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@CrossOrigin(origins = "http://192.168.1.18:3000")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping(path = "/restaurants/{restaurantId}/menu")
    public List<MenuItems> getMenu(@PathVariable int restaurantId){
        return menuService.getMenuByRestaurantId(restaurantId);
    }
}
