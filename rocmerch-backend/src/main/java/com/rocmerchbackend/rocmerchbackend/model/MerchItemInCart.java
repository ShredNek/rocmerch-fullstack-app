package com.rocmerchbackend.rocmerchbackend.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

@Entity
@Table(name = "merch_item_in_cart")
public class MerchItemInCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName ="id")
    @JsonProperty(value = "orderId")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private MerchandiseOrders merchandiseOrders;

    @ManyToOne
    @JoinColumn(name = "merchandise_id", referencedColumnName = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    private MerchandiseItems merchandiseItem;

    // Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public MerchandiseItems getMerchandiseItem() {
        return merchandiseItem;
    }

    public void setMerchandiseItem(MerchandiseItems merchandiseItem) {
        this.merchandiseItem = merchandiseItem;
    }

    public MerchandiseOrders getMerchandiseOrders() {
        return merchandiseOrders;
    }

    public void setMerchandiseOrders(MerchandiseOrders merchandiseOrders) {
        this.merchandiseOrders = merchandiseOrders;
    }
}
