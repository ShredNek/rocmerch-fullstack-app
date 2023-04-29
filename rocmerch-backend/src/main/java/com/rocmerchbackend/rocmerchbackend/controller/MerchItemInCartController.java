package com.rocmerchbackend.rocmerchbackend.controller;

import com.rocmerchbackend.rocmerchbackend.model.MerchItemInCart;
import com.rocmerchbackend.rocmerchbackend.repository.MerchItemInCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MerchItemInCartController {

    @Autowired
    private MerchItemInCartRepository merchItemInCartRepository;

    MerchItemInCart newMerchItemInCart(@RequestBody MerchItemInCart newMerchItemInCart) {
        return merchItemInCartRepository.save(newMerchItemInCart);
    }

    List<MerchItemInCart> getAllMerchItemsInCart() {
        return merchItemInCartRepository.findAll();
    }
}
