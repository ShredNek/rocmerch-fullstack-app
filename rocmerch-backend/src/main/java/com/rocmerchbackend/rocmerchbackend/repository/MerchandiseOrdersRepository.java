package com.rocmerchbackend.rocmerchbackend.repository;

import com.rocmerchbackend.rocmerchbackend.model.MerchandiseOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchandiseOrdersRepository extends JpaRepository<MerchandiseOrders, Long> {

    @Query("SELECT MAX(id) FROM MerchandiseOrders")
    Long findMaxId();

}
