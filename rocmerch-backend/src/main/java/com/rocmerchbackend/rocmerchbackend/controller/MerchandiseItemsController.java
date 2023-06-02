package com.rocmerchbackend.rocmerchbackend.controller;

import com.rocmerchbackend.rocmerchbackend.model.MerchandiseItems;
import com.rocmerchbackend.rocmerchbackend.exception.ItemNotFoundException;
import com.rocmerchbackend.rocmerchbackend.repository.MerchandiseItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/items")
public class MerchandiseItemsController {
    @Autowired
    private MerchandiseItemsRepository merchandiseItemsRepository;

    @PostMapping("/new")
    public String newItem(@RequestBody MerchandiseItems newItem) throws Exception {
        try {
            merchandiseItemsRepository.save(newItem);
        } catch (Exception e) {
            return e.toString();
        }
        return "Saved new "+newItem.getName()+" to id: "+newItem.getId();
    }

    @GetMapping("/all")
    public List<MerchandiseItems> getAllMerchandiseItems() {
        return merchandiseItemsRepository.findAll();
    }

    @GetMapping("/get-by-id/{id}")
    public MerchandiseItems getMerchItemById(@PathVariable Long id) {
        return merchandiseItemsRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    @GetMapping("/get-by-category/{category}")
    public List<MerchandiseItems> getMerchItemsByCategory(@PathVariable("category") String category) {
        return merchandiseItemsRepository.getMerchandiseItemsByCategory(category);
    }

    @GetMapping("/get-by-name/{name}")
    public ArrayList<Optional<MerchandiseItems>> getMerchItemsByName(@PathVariable("name") String nameInput) {

        List<MerchandiseItems> allItems = merchandiseItemsRepository.findAll();
        HashMap<Long, Integer> matchingWords = new HashMap<>();
        String[] splitNameInput = nameInput.split("(?!^)");
        ArrayList<Optional<MerchandiseItems>> sortedMatchedWords = new ArrayList<>();

        allItems.forEach(i -> {

            if (nameInput.equalsIgnoreCase(i.getName()))
                sortedMatchedWords.add(merchandiseItemsRepository.findById(i.getId()));

            String[] itemSplit = i.getName().split("(?!^)");

            AtomicInteger matchingLetters = new AtomicInteger(0);

            Arrays.stream(splitNameInput).forEach(inputLetter -> {
                AtomicBoolean wasAlreadyPresent = new AtomicBoolean(false);
                Arrays.stream(itemSplit).forEach(itemLetter -> {

                    if (inputLetter.equalsIgnoreCase(itemLetter) && !wasAlreadyPresent.get()) {
                        wasAlreadyPresent.set(true);
                        matchingLetters.getAndIncrement();
                    }
                });
            });
            matchingWords.put(i.getId(), matchingLetters.get());
        });

        AtomicInteger maxLetterMatch = new AtomicInteger(Collections.max(matchingWords.values()));

        while (maxLetterMatch.get() != 0) {
            matchingWords.forEach((id, numberOfMatches) -> {
                if (numberOfMatches.equals(maxLetterMatch.get()) && !sortedMatchedWords.contains(merchandiseItemsRepository.findById(id))) {
                    sortedMatchedWords.add(merchandiseItemsRepository.findById(id));
                }
            });
            maxLetterMatch.getAndDecrement();
        }
        return sortedMatchedWords;
    }

    @PutMapping("/put/{id}")
    public MerchandiseItems updateItem(@RequestBody MerchandiseItems newItem, @PathVariable Long id) {
        return merchandiseItemsRepository.findById(id).map(item -> {
            item.setName(newItem.getName());
            item.setItemDescription(newItem.getItemDescription());
            item.setCategory(newItem.getCategory());
            item.setPrice(newItem.getPrice());
            item.setImage(newItem.getImage());
            return merchandiseItemsRepository.save(item);
        }).orElseThrow(() -> new ItemNotFoundException(id));

    }

    @DeleteMapping("/delete/{id}")
    String deleteItem(@PathVariable Long id) {
        if (!merchandiseItemsRepository.existsById(id)) {
            throw new ItemNotFoundException(id);
        }
//        merchandiseItemsRepository.deleteById(id);
//        return "Item with id " + id + " has been deleted.";
        return "DANGER. ATTEMPTED TO DELETE ID: " + id + ". DELETE HAS BEEN DISABLED.";
    }
}
