package com.foodapp.discount_service.controller;

import com.foodapp.discount_service.entity.Discount;
import com.foodapp.discount_service.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class DiscountController {

    @Autowired
    DiscountService discountService;

    @GetMapping(path = "/discounts")
    public List<Discount> getAllDiscounts(){
        return discountService.getAllDiscounts();
    }

    @GetMapping(path = "/discounts/by-code/{code}")
    public Discount getDiscountByCode(@PathVariable String code){
        return discountService.getDiscountByCode(code);
    }

    @GetMapping(path = "/discounts/{id}")
    public Discount getDiscountById(@PathVariable int id){
        return discountService.getDiscountById(id);
    }
}
