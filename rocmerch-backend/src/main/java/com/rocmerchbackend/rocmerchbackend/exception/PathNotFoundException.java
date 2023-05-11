package com.rocmerchbackend.rocmerchbackend.exception;

public class PathNotFoundException extends RuntimeException {
    public PathNotFoundException(Long path) {
        super("Could not find path "+path);
    }
}
