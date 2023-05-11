package com.rocmerchbackend.rocmerchbackend.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "merchandise_orders")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class MerchandiseOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameForOrder;
    private String emailForOrder;
    @Column(name = "total_price", columnDefinition = "DECIMAL(10,2)")
    private BigDecimal totalPrice;
    @OneToMany(mappedBy = "merchandiseOrders", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<MerchItemInCart> merchItemsInCart = new ArrayList<>();

    // Methods
    public MerchItemInCart getShoppingCartItemByIndex(int index) {
        return merchItemsInCart.get(index);
    }

    public BigDecimal calculateTotalPrice() {
        return merchItemsInCart.stream()
                .map(i-> i
                        .getMerchandiseItem().getPrice()
                        .multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<MerchItemInCart> getMerchItemsInCart() {
        return merchItemsInCart;
    }

    public void setMerchItemsInCart(List<MerchItemInCart> merchItemsInCart) {
        this.merchItemsInCart = merchItemsInCart;
    }
}