package com.foodapp.discount_service.controller;

import com.foodapp.discount_service.entity.Discount;
import com.foodapp.discount_service.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class DiscountController {

    @Autowired
    DiscountService discountService;

    @GetMapping(path = "discounts/all")
    public List<Discount> getAllDiscounts(){
        return discountService.getAllDiscounts();
    }
}
