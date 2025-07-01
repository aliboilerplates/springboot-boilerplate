package com.example.demo.tutorial.dto;

public class UpdateTutorialDto {
    private String title;
    private String description;
    private Boolean published;
    
    
    public Boolean getPublished() {
        return published;
    }
    
    public void setPublished(Boolean published) {
        this.published = published;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
}
