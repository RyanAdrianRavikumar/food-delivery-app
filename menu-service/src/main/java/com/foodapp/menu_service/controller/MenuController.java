package com.foodapp.menu_service.controller;

import com.foodapp.menu_service.entity.MenuItems;
import com.foodapp.menu_service.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

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

    @PutMapping("/menu/{menuItemId}/price")
    public ResponseEntity<MenuItems> updateMenuItemPrice(
            @PathVariable int menuItemId,
            @RequestBody Map<String, Double> requestBody) {

        // Extract "price" from JSON body
        Double newPrice = requestBody.get("price");

        // Validate newPrice (ensure it's not null or negative)
        if (newPrice == null || newPrice <= 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        MenuItems updatedMenuItem = menuService.updateMenuItemPrice(menuItemId, newPrice);

        return updatedMenuItem == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(updatedMenuItem, HttpStatus.OK);
    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity<String> deleteMenuItem(@PathVariable int id) {
        try {
            menuService.deleteMenuItem(id);
            return ResponseEntity.ok("Menu item deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}