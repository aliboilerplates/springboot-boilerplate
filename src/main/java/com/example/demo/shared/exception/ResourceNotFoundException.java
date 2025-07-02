package com.example.demo.shared.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends HttpException {
    
    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
