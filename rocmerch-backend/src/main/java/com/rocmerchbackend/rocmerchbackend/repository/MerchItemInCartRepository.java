package com.rocmerchbackend.rocmerchbackend.repository;

import com.rocmerchbackend.rocmerchbackend.model.MerchItemInCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MerchItemInCartRepository extends JpaRepository<MerchItemInCart, Long> {

    @Query("SELECT MAX(id) FROM MerchItemInCart")
    Long findMaxId();
}
