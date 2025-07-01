package com.example.demo.payload;


import org.springframework.http.HttpStatus;

public class ApiResponseBody<T> {
    
    private int statusCode;
    
    private String message;
    
    private T data;
    
    public ApiResponseBody() {
    
    }
    
    public ApiResponseBody(String message, HttpStatus status, T data) {
        this.statusCode = status.value();
        this.message = message;
        this.data = data;
    }
    
    public ApiResponseBody(String message, HttpStatus status) {
        this.message = message;
        this.statusCode = status.value();
    }
    
    public ApiResponseBody(T data) {
        this.message = "Success";
        this.statusCode = HttpStatus.OK.value();
        this.data = data;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public int getStatusCode() {
        return statusCode;
    }
    
    public void setStatusCode(HttpStatus status) {
        this.statusCode = status.value();
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
}
