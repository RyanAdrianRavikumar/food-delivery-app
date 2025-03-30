package com.foodapp.discount_service.service;

import com.foodapp.discount_service.entity.Discount;
import com.foodapp.discount_service.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DiscountService {

    @Autowired
    DiscountRepository discountRepository;

    public List<Discount> getAllDiscounts(){
        return discountRepository.findAll();
    }

    public Discount getDiscountByCode(String code){
        return discountRepository.findByCode(code);
    }

    public Discount getDiscountById(int id) {
        Optional<Discount> optionalDiscount = discountRepository.findById(id);

        if (optionalDiscount.isPresent()) {
            return optionalDiscount.get();
        } else {
            throw new RuntimeException("Discount not found with id: " + id);
        }
    }
}
