package com.example.demo.tutorial.model;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class Tutorial {
    @Id private UUID id;
    private String title;
    private String description;
    private boolean published;
    
    public Tutorial() {
    }
    
    ;
    
    Tutorial(UUID id, String title, String description, Boolean published) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.published = published;
    }
    
    Tutorial(String title, String description, Boolean published) {
        this.title = title;
        this.description = description;
        this.published = published;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public UUID getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public boolean isPublished() {
        return published;
    }
    
    public void setPublished(boolean isPublished) {
        this.published = isPublished;
    }
    
    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
    }
    
    
}
