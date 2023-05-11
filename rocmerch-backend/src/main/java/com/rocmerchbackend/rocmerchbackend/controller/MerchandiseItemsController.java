package com.rocmerchbackend.rocmerchbackend.controller;

import com.rocmerchbackend.rocmerchbackend.exception.CategoryNotFoundException;
import com.rocmerchbackend.rocmerchbackend.model.MerchandiseItems;
import com.rocmerchbackend.rocmerchbackend.exception.ItemNotFoundException;
import com.rocmerchbackend.rocmerchbackend.repository.MerchandiseItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class MerchandiseItemsController {
    @Autowired
    private MerchandiseItemsRepository merchandiseItemsRepository;

    @PostMapping("/new")
    MerchandiseItems newItem(@RequestBody MerchandiseItems newItem) {
        return merchandiseItemsRepository.save(newItem);
    }

    @GetMapping("/all")
    List<MerchandiseItems> getAllMerchandiseItems(){
        return merchandiseItemsRepository.findAll();
    }

    @GetMapping("/get-by-id/{id}")
    MerchandiseItems getMerchItemById(@PathVariable Long id){
        return merchandiseItemsRepository.findById(id)
                .orElseThrow(()-> new ItemNotFoundException(id));
    }

    @GetMapping("/get-by-category/{category}")
    List<MerchandiseItems> getMerchItemsByCategory(@PathVariable("category") String category) {
        return merchandiseItemsRepository.getMerchandiseItemsByCategory(category);
    }

    @PutMapping("/put/{id}")
    MerchandiseItems updateItem(@RequestBody MerchandiseItems newItem, @PathVariable Long id) {
            return merchandiseItemsRepository.findById(id)
                    .map(item -> {
                        item.setName(newItem.getName());
                        item.setItemDescription(newItem.getItemDescription());
                        item.setCategory(newItem.getCategory());
                        item.setPrice(newItem.getPrice());
                        return merchandiseItemsRepository.save(item);
                    }).orElseThrow(()-> new ItemNotFoundException(id));

    }

    @DeleteMapping("/delete/{id}")
    String deleteItem(@PathVariable Long id) {
        if(!merchandiseItemsRepository.existsById(id)) {
            throw new ItemNotFoundException(id);
        }
        merchandiseItemsRepository.deleteById(id);
        return "Item with id "+id+" has been deleted.";
    }
}
