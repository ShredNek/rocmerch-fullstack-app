package com.rocmerchbackend.rocmerchbackend.exception;

public class ItemNotFoundException extends RuntimeException{
    public ItemNotFoundException(Long id){
        super("Could not find the user with the id matching "+id);
    }
}
