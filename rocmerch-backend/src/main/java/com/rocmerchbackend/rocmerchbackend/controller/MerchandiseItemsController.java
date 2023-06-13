package com.rocmerchbackend.rocmerchbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rocmerchbackend.rocmerchbackend.UtilsFunctions;
import com.rocmerchbackend.rocmerchbackend.model.MerchandiseItems;
import com.rocmerchbackend.rocmerchbackend.exception.ItemNotFoundException;
import com.rocmerchbackend.rocmerchbackend.repository.MerchandiseItemsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.SyncFailedException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/items")
public class MerchandiseItemsController {
    @Autowired
    private MerchandiseItemsRepository merchandiseItemsRepository;

    @Transactional
    @PostMapping(path = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String newItem(@RequestBody String userItemInput) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            MerchandiseItems[] itemsArray = objectMapper.readValue(userItemInput, MerchandiseItems[].class);

            if (itemsArray.length == 1) {
                MerchandiseItems singleItem = itemsArray[0];
                merchandiseItemsRepository.save(singleItem);
                return "Saved new " + singleItem.getName() + " to id: " + singleItem.getId();
            } else {
                List<MerchandiseItems> itemList = Arrays.asList(itemsArray);
                var allUserItemNames = new ArrayList<String>();
                var allUserItemIds = new ArrayList<Long>();

                for (MerchandiseItems item : itemList) allUserItemNames.add(item.getName());

                merchandiseItemsRepository.saveAll(itemList);

                for (MerchandiseItems item : itemList) allUserItemIds.add(item.getId());

                return "Saved these items: " + allUserItemNames.toString() + " to these id's: " + allUserItemIds.toString();
            }
        } catch (com.fasterxml.jackson.databind.exc.MismatchedInputException e) {
            try {
                MerchandiseItems singleItem = objectMapper.readValue(userItemInput, MerchandiseItems.class);
                merchandiseItemsRepository.save(singleItem);
                return "Saved new " + singleItem.getName() + " to id: " + singleItem.getId();
            } catch (Exception ex) {
                return ex.toString();
            }
        } catch (Exception e) {
            return e.toString();
        }
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
    public List<MerchandiseItems> optimisedGetMerchItemsByName(@PathVariable("name") String nameInput) {
        TreeMap<Long, Integer> matchingWords = new TreeMap<>();
        String[] splitNameInput = nameInput.split("(?!^)");
        ArrayList<MerchandiseItems> sortedMatchedWords = new ArrayList<>();
        int maxItemsReturned = 3;

        List<MerchandiseItems> calledItems = UtilsFunctions.getCachedMerchItems();
        if (calledItems == null || calledItems.isEmpty()) {
            calledItems = merchandiseItemsRepository.findAll();
        }

        if (calledItems.isEmpty()) return sortedMatchedWords;

        calledItems.forEach(i -> {
            if (nameInput.equalsIgnoreCase(i.getName()))
                sortedMatchedWords.add(i);

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

        AtomicInteger i = new AtomicInteger();

        while (maxLetterMatch.get() != 0 && i.get() < maxItemsReturned) {
            matchingWords.forEach((id, numberOfMatches) -> {
                if (numberOfMatches.equals(maxLetterMatch.get()) && !sortedMatchedWords.contains(UtilsFunctions.findCachedMerchItemById(id))) {
                    sortedMatchedWords.add(UtilsFunctions.findCachedMerchItemById(id));
                    i.getAndIncrement();
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
