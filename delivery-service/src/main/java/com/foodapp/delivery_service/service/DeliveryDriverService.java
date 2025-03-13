package com.foodapp.delivery_service.service;

import com.foodapp.delivery_service.entity.DeliveryDriver;
import com.foodapp.delivery_service.repository.DeliveryDriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeliveryDriverService {

    @Autowired
    private DeliveryDriverRepository deliveryDriverRepository;

    // get all delivery drivers
    public List<DeliveryDriver> getAllDrivers() {
        return deliveryDriverRepository.findAll();
    }

    // register delivery drivers
    public String registerDeliveryDriver(DeliveryDriver deliveryDriver){
        if(deliveryDriverRepository.findByEmail(deliveryDriver.getEmail()) != null){
            return "Email is already in use.";
        }

        deliveryDriverRepository.save(deliveryDriver);
        return "Driver registered successfully.";
    }
}
