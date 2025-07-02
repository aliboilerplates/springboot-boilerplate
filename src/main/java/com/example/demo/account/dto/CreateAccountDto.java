package com.example.demo.account.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CreateAccountDto {
    @NotBlank(message = "name is required")
    private String name;
    
    @NotBlank(message = "email is required")
    @Email(message = "invalid email address")
    private String email;
    
    @NotBlank(message = "password is required")
    @Pattern(
            regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message =
            "Password must be minimum eight characters, at least one uppercase letter, one " +
                    "lowercase letter, one number and one special character"
    )
    private String password;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}
