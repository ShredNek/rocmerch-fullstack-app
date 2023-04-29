package com.rocmerchbackend.rocmerchbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private BigDecimal totalPrice;
    private String nameForOrder;
    private String emailForOrder;
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//    @JsonSerialize(contentAs = MerchItemInCart.class)
    private List<MerchItemInCart> merchItemInCart = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getNameForOrder() {
        return nameForOrder;
    }

    public void setNameForOrder(String nameForOrder) {
        this.nameForOrder = nameForOrder;
    }

    public String getEmailForOrder() {
        return emailForOrder;
    }

    public void setEmailForOrder(String emailForOrder) {
        this.emailForOrder = emailForOrder;
    }

    public List<MerchItemInCart> getMerchItemInCart() {
        return merchItemInCart;
    }

    public void setMerchItemInCart(List<MerchItemInCart> merchItemInCart) {
        this.merchItemInCart = merchItemInCart;
    }
}
