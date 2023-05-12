package com.rocmerchbackend.rocmerchbackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.rocmerchbackend.rocmerchbackend.exception.CouldNotFindItemById;
import com.rocmerchbackend.rocmerchbackend.model.MerchItemInCart;
import com.rocmerchbackend.rocmerchbackend.model.MerchandiseItems;
import com.rocmerchbackend.rocmerchbackend.model.MerchandiseOrders;
import com.rocmerchbackend.rocmerchbackend.repository.MerchItemInCartRepository;
import com.rocmerchbackend.rocmerchbackend.repository.MerchandiseOrdersRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/orders")
public class MerchandiseOrdersController {
    @Autowired
    private MerchandiseOrdersRepository merchandiseOrdersRepository;
    @Autowired
    private MerchItemInCartRepository merchItemInCartRepository;

    public Long generateUniqueId() {
        Long idIfMaxIdReturnsNull = (long) 1;
        Long maxId = merchandiseOrdersRepository.findMaxId();
        if (maxId == null) return idIfMaxIdReturnsNull;
        return maxId + 1;
    }

    @PostMapping(value = "/new", produces = "application/json")
    public String newOrder(@RequestBody MerchandiseOrders newOrder) throws JsonProcessingException {

        Long parentId = generateUniqueId();
        try {
            newOrder.setId(parentId);
            Hibernate.initialize(newOrder.getMerchItemsInCart());

            // Serialises each merchItemInCart to the parent id by initialising
            // the new order as the parent
            newOrder.getMerchItemsInCart()
                    .forEach(item -> item.setMerchandiseOrders(newOrder));

            if (newOrder.getTotalPrice().equals(BigDecimal.ZERO)) {
                newOrder.setTotalPrice(newOrder.calculateTotalPrice());
            }

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String json = objectMapper.writeValueAsString(newOrder);
            System.out.println(json);

            merchandiseOrdersRepository.save(newOrder);
        } catch (Exception e) {
            return "I'm afraid we couldn't process the json. Please email us @ danielleemusic98@gmail.com for assistance. Here's the error message: "+e.toString();
        }
        return "Shopping Cart saved to the Id of " + parentId;
    }

    @GetMapping("/all")
    public List<MerchandiseOrders> getShoppingCarts() {
        return merchandiseOrdersRepository.findAll();
    }

    @GetMapping("/cart/{id}")
    public Optional<MerchandiseOrders> getShoppingCartById(@PathVariable Long id) {
        return merchandiseOrdersRepository.findById(id);
    }

    @PutMapping("/edit/{id}")
    public String editShoppingCard(@PathVariable Long id, @RequestBody MerchandiseOrders editedCart) throws CouldNotFindItemById {
        BigDecimal newTotalPrice;
        try {
            MerchandiseOrders oldOrder = merchandiseOrdersRepository.findById(id).orElseThrow(() -> new CouldNotFindItemById(id));
            if (editedCart.getId() == null) return "You didn't send any data! Try again";

            ArrayList<Long> newCartItemIds = new ArrayList<>();
            editedCart.getMerchItemsInCart().forEach(i -> {
                newCartItemIds.add(i.getId());
            });


            AtomicInteger iteration = new AtomicInteger(0);
            oldOrder.getMerchItemsInCart().forEach(i -> {
                Long currentIterationId = newCartItemIds.get(iteration.get());
                if (i.getId().equals(currentIterationId)) {
                    MerchItemInCart validCartItem = null;
                    try {
                        validCartItem = merchItemInCartRepository.findById(currentIterationId)
                                .orElseThrow(() -> new CouldNotFindItemById(currentIterationId));
                    } catch (CouldNotFindItemById e) {
                        throw new RuntimeException(e);
                    }
                    MerchandiseItems currentCartItemStock = editedCart.getShoppingCartItemByIndex(iteration.get()).getMerchandiseItem();
                    validCartItem.getMerchandiseItem().setName(currentCartItemStock.getName());
                    validCartItem.getMerchandiseItem().setCategory(currentCartItemStock.getCategory());
                    validCartItem.getMerchandiseItem().setItemDescription(currentCartItemStock.getItemDescription());
                    validCartItem.getMerchandiseItem().setPrice(currentCartItemStock.getPrice());
                    merchItemInCartRepository.save(validCartItem);
                }
                iteration.getAndIncrement();
            });
            newTotalPrice = editedCart.calculateTotalPrice();
            System.out.println(newTotalPrice);
            oldOrder.setTotalPrice(newTotalPrice);
            Hibernate.initialize(oldOrder);
//            merchandiseOrdersRepository.save(oldOrder);
        } catch (Exception e) {
            return e.toString();
        }
        return "Successfully edited cart Id: " + id;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCartById(@PathVariable Long id) {
        try {
            merchandiseOrdersRepository.deleteById(id);
        } catch (Exception e) {
            return new CouldNotFindItemById(id).toString();
        }
        return "ShoppingCart of Id " + id + " has been deleted.";
    }


    @DeleteMapping("/wipe-all")
    public String wipeAllCarts() {
//        merchandiseOrdersRepository.deleteAll();
        return "DANGEROUS METHOD SPOTTED. PLEASE UNCOMMENT CODE TO COMMIT TO THIS ACTION.";
    }
}


