package com.rocmerchbackend.rocmerchbackend.model;

import com.rocmerchbackend.rocmerchbackend.repository.OrdersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrdersRepository ordersRepository;

    public Orders createOrders(Long userId, List<MerchItemInCart> merchItemInCart) {
        Orders orders = new Orders();
        orders.setUserId(userId);
        orders.setMerchItemInCart(merchItemInCart);
        BigDecimal totalPrice = merchItemInCart.stream()
                .map(orderItem -> orderItem.getMerchandiseItem().getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        orders.setTotalPrice(totalPrice);
        ordersRepository.save(orders);
        return orders;
    }}
