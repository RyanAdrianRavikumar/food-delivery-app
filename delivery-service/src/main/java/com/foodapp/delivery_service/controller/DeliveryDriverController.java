package com.foodapp.delivery_service.controller;

import com.foodapp.delivery_service.entity.DeliveryDriver;
import com.foodapp.delivery_service.service.DeliveryDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeliveryDriverController {

    @Autowired
    DeliveryDriverService deliveryDriverService;

    @GetMapping(path = "drivers/all")
    public List<DeliveryDriver> getAllDrivers(){
        return deliveryDriverService.getAllDrivers();
    }

    @PostMapping(path = "drivers/register")
    public String registerDeliveryDriver(@RequestBody DeliveryDriver deliveryDriver){
        return deliveryDriverService.registerDeliveryDriver(deliveryDriver);
        }
}
