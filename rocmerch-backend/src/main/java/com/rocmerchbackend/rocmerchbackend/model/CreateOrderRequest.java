package com.rocmerchbackend.rocmerchbackend.model;

import java.util.List;

public class CreateOrderRequest {
    private Long userId;
    private List<MerchItemInCart> merchItemInCart;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<MerchItemInCart> getMerchItemInCart() {
        return merchItemInCart;
    }

    public void setMerchItemInCart(List<MerchItemInCart> merchItemInCart) {
        this.merchItemInCart = merchItemInCart;
    }
}