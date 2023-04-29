package com.rocmerchbackend.rocmerchbackend.repository;

import com.rocmerchbackend.rocmerchbackend.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

}
