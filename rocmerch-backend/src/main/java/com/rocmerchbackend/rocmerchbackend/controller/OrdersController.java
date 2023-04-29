package com.rocmerchbackend.rocmerchbackend.controller;

import com.rocmerchbackend.rocmerchbackend.model.CreateOrderRequest;
import com.rocmerchbackend.rocmerchbackend.model.MerchItemInCart;
import com.rocmerchbackend.rocmerchbackend.model.OrderService;
import com.rocmerchbackend.rocmerchbackend.model.Orders;
import com.rocmerchbackend.rocmerchbackend.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrderService orderService;
//    private OrdersRepository ordersRepository;

    // ? Old code
//    @PostMapping("/new-order")
//    Orders newOrder(@RequestBody Orders newOrder) {
//        return ordersRepository.save(newOrder);
//    }

//    @GetMapping("/orders")
//    List<Orders> getAllOrders() {
//        return ordersRepository.findAll();
//    }

    @PostMapping("/new-order")
    public Orders createOrders(@RequestBody CreateOrderRequest request) {
        List<MerchItemInCart> merchItemInCart = request.getMerchItemInCart();
        Long userId = request.getUserId();
        return orderService.createOrders(userId, merchItemInCart);
    }


}


