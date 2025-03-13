package com.foodapp.restaurant_service.service;

import com.foodapp.restaurant_service.entity.Restaurant;
import com.foodapp.restaurant_service.repository.RestuarantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    RestuarantRepository restuarantRepository;

    public List<Restaurant> getAllRestaurants(){
        return restuarantRepository.findAll();
    }

    public Restaurant getRestaurantById(int id){
        Optional<Restaurant> restaurant = restuarantRepository.findById(id);
        if(restaurant.isPresent()){
            return restaurant.get();
        }
        return null;
    }
}
