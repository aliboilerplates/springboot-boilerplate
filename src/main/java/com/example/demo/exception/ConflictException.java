package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends HttpException {
    
    public ConflictException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
