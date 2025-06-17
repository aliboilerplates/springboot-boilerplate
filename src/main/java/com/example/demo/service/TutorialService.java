package com.example.demo.service;

import com.example.demo.dto.CreateTutorialDto;
import com.example.demo.exception.InternalServerException;
import com.example.demo.model.Tutorial;
import com.example.demo.repositary.TutorialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TutorialService {
    private final static Logger logger =
            LoggerFactory.getLogger(TutorialService.class);
    
    
    private final TutorialRepository tutorialRepository;
    
    public TutorialService(TutorialRepository tutorialRepository) {
        this.tutorialRepository = tutorialRepository;
    }
    
    public long create(CreateTutorialDto createTutorialDto) {
        try {
            Tutorial tutorial = new Tutorial();
            tutorial.setTitle(createTutorialDto.getTitle());
            tutorial.setDescription(createTutorialDto.getDescription());
            tutorial.setPublished(createTutorialDto.getPublished());
            
            tutorialRepository.save(tutorial);
            return tutorial.getId();
        } catch (DataAccessException e) {
            logger.error("Failed to create tutorial {}", e.getMessage(), e);
            throw new InternalServerException();
        }
    }
}
