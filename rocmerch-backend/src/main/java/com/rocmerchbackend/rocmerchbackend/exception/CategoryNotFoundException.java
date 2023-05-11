package com.rocmerchbackend.rocmerchbackend.exception;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(String category){
        super("Could not find the item with the category matching "+category);
    }
}
