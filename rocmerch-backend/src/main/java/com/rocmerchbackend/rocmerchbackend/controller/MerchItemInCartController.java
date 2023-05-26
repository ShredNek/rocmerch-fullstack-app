package com.rocmerchbackend.rocmerchbackend.controller;

import com.rocmerchbackend.rocmerchbackend.model.MerchItemInCart;
import com.rocmerchbackend.rocmerchbackend.repository.MerchItemInCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping-cart-item")
public class MerchItemInCartController {
    @Autowired
    private MerchItemInCartRepository shoppingCartItemRepository;

    @PostMapping("/new")
    public String newShoppingCartItem(@RequestBody MerchItemInCart newMerchItemInCart) {
        try {
            shoppingCartItemRepository.save(newMerchItemInCart);
        } catch (Exception e)
        {
            System.out.println(e.toString());
            return e.toString();
        }
        return "Your item "+newMerchItemInCart.getId()
                +": with the quantity of "
                +newMerchItemInCart.getQuantity()
                +" and stock item "
                +newMerchItemInCart.getMerchandiseItem().getName()
                + ", has been added successfully.";
    }

    @GetMapping("/all")
    public List<MerchItemInCart> getAllShoppingCartItems() {
        return shoppingCartItemRepository.findAll();
    }

    @DeleteMapping("/wipe-all")
    public String wipeAllData() {
//        shoppingCartItemRepository.deleteAll();
        return "shoppingCartItemRepository wiped.";
    }


}
