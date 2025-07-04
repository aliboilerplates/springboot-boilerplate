package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class InternalServerException extends HttpException {
    
    public InternalServerException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    public InternalServerException() {
        super("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
