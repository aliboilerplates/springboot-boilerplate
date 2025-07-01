package com.example.demo.repositary;

import com.example.demo.model.Tutorial;


import java.util.List;
import java.util.UUID;

public interface TutorialRepository {
    Tutorial save(Tutorial tutorial);
    
    Tutorial update(Tutorial tutorial);
    
    Tutorial findById(UUID id);
    
    void deleteById(UUID id);
    
    List<Tutorial> findAll();
    
    List<Tutorial> findByPublished(boolean published);
    
    List<Tutorial> findByTitleContaining(String title);
    
    void deleteAll();
    
}
