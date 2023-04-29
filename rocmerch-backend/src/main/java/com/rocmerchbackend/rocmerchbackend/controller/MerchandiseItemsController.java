package com.rocmerchbackend.rocmerchbackend.controller;

import com.rocmerchbackend.rocmerchbackend.model.MerchandiseItems;
import com.rocmerchbackend.rocmerchbackend.exception.ItemNotFoundException;
import com.rocmerchbackend.rocmerchbackend.repository.MerchandiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MerchandiseItemsController {
    @Autowired
    private MerchandiseRepository merchandiseRepository;

    @PostMapping("/new-item")
    MerchandiseItems newItem(@RequestBody MerchandiseItems newItem) {
        return merchandiseRepository.save(newItem);
    }

    @GetMapping("/items")
    List<MerchandiseItems> getAllMerchandiseItems(){
        return merchandiseRepository.findAll();
    }

    @GetMapping("/item/{id}")
    MerchandiseItems getMerchItemById(@PathVariable Long id){
        return merchandiseRepository.findById(id)
                .orElseThrow(()-> new ItemNotFoundException(id));
    }

    @PutMapping("/item/{id}")
    MerchandiseItems updateItem(@RequestBody MerchandiseItems newItem, @PathVariable Long id) {
            return merchandiseRepository.findById(id)
                    .map(item -> {
                        item.setName(newItem.getName());
                        item.setItemDescription(newItem.getItemDescription());
                        item.setCategory(newItem.getCategory());
                        item.setPrice(newItem.getPrice());
                        return merchandiseRepository.save(item);
                    }).orElseThrow(()-> new ItemNotFoundException(id));

    }

    @DeleteMapping("/item/{id}")
    String deleteItem(@PathVariable Long id) {
        if(!merchandiseRepository.existsById(id)) {
            throw new ItemNotFoundException(id);
        }
        merchandiseRepository.deleteById(id);
        return "User with id "+id+" has been deleted.";
    }
}
