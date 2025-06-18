package com.example.demo.model;

public class Tutorial {
    private String id;
    private String title;
    private String description;
    private boolean published;
    
    public Tutorial() {
    }
    
    ;
    
    Tutorial(String id, String title, String description, Boolean published) {
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
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getId() {
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
