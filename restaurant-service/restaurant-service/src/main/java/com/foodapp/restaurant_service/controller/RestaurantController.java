package com.foodapp.restaurant_service.controller;

import com.foodapp.restaurant_service.entity.Restaurant;
import com.foodapp.restaurant_service.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://192.168.1.18:3000")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(path = "/restaurants")
    public List<Restaurant> getAllRestaurants(){
        return restaurantService.getAllRestaurants();
    }

    @GetMapping(path = "/restaurants/{id}")
    public Restaurant getRestaurantById(@PathVariable int id){
        return restaurantService.getRestaurantById(id);
    }
}
