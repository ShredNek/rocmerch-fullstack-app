package com.rocmerchbackend.rocmerchbackend.exception;

public class CouldNotFindItemById extends Exception{
    public CouldNotFindItemById(Long id) {
        super("Could not find item that contains id "+id);
    }
}
