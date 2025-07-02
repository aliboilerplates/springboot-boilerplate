package com.example.demo.shared.exception;

import org.springframework.http.HttpStatus;

public class HttpException extends RuntimeException {
    private final HttpStatus statusCode;
    
    HttpException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
    
    
    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
