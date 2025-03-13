package com.foodapp.discount_service.service;

import com.foodapp.discount_service.entity.Discount;
import com.foodapp.discount_service.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DiscountService {

    @Autowired
    DiscountRepository discountRepository;

    public List<Discount> getAllDiscounts(){
        return discountRepository.findAll();
    }
}
