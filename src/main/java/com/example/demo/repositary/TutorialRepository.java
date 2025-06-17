package com.example.demo.repositary;

import com.example.demo.model.Tutorial;

import java.util.List;

public interface TutorialRepository {
    int save(Tutorial tutorial);
    
    int update(Tutorial tutorial);
    
    Tutorial findById(Long id);
    
    int deleteById(Long id);
    
    List<Tutorial> findAll();
    
    List<Tutorial> findByPublished(boolean published);
    
    List<Tutorial> findByTitleContaining(String title);
    
    int deleteAll();
    
}
