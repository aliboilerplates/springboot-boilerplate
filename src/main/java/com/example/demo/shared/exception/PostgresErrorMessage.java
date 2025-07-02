package com.example.demo.shared.exception;

public class PostgresErrorMessage {
    private String uniqueConstraintViolationMessage;
    
    public PostgresErrorMessage(String uniqueConstraintViolationMessage) {
        this.uniqueConstraintViolationMessage = uniqueConstraintViolationMessage;
    }
    
    public PostgresErrorMessage() {
        this.uniqueConstraintViolationMessage = "A record with same info already exists";
    }
    
    public String getUniqueConstraintViolationMessage() {
        return uniqueConstraintViolationMessage;
    }
    
    public void setUniqueConstraintViolationMessage(String message) {
        this.uniqueConstraintViolationMessage = message;
    }
}
