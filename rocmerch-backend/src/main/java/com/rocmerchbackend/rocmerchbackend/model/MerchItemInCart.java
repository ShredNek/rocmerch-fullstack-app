package com.rocmerchbackend.rocmerchbackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "merch_item_in_cart")
public class MerchItemInCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // ? Old code ?
//    private Long quantity;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "merchandise_item_id", referencedColumnName = "id")
//    private MerchandiseItems merchandiseItem;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id", referencedColumnName = "id")
//    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private MerchandiseItems merchandiseItems;

    private Long quantity;

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public MerchandiseItems getMerchandiseItem() {
        return merchandiseItems;
    }

    public void setMerchandiseItem(MerchandiseItems merchandiseItem) {
        this.merchandiseItems = merchandiseItem;
    }
}
