package com.foodapp.menu_service.controller;

import com.foodapp.menu_service.entity.MenuItems;
import com.foodapp.menu_service.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/menu/{menuItemId}")
    public ResponseEntity<MenuItems> getMenuItemById(@PathVariable int menuItemId) {
        MenuItems menuItem = menuService.findById(menuItemId);
        if (menuItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(menuItem, HttpStatus.OK);
    }
}
