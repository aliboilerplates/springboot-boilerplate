package com.example.demo.service;

import com.example.demo.dto.CreateTutorialDto;
import com.example.demo.dto.UpdateTutorialDto;
import com.example.demo.exception.PostgresErrorHandler;
import com.example.demo.exception.PostgresErrorMessage;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.TutorialMapper;
import com.example.demo.model.Tutorial;
import com.example.demo.repositary.TutorialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TutorialService {
    private final static Logger logger =
            LoggerFactory.getLogger(TutorialService.class);
    
    
    private final TutorialRepository tutorialRepository;
    private final TutorialMapper tutorialMapper;
    
    public TutorialService(TutorialRepository tutorialRepository, TutorialMapper tutorialMapper) {
        this.tutorialMapper = tutorialMapper;
        this.tutorialRepository = tutorialRepository;
    }
    
    public Tutorial create(CreateTutorialDto createTutorialDto) {
        try {
            Tutorial tutorial = this.tutorialMapper.toEntity(createTutorialDto);
            return tutorialRepository.save(tutorial);
        } catch (DataAccessException e) {
            logger.error("Failed to create tutorial {}", e.getMessage(), e);
            throw PostgresErrorHandler.mapToHttpException(
                    e, new PostgresErrorMessage(
                            "A tutorial with the same title already exists"
                    )
            );
        }
    }
    
    public Tutorial update(UUID id, UpdateTutorialDto updateTutorialDto) {
        try {
            Tutorial existingTutorial = this.tutorialRepository.findById(id);
            
            if (existingTutorial == null) throw new ResourceNotFoundException("Tutorial not Found");
            
            this.tutorialMapper.updateEntityFromDto(updateTutorialDto, existingTutorial);
            
            return tutorialRepository.update(existingTutorial);
        } catch (DataAccessException e) {
            logger.error("Failed to update tutorial with id: {}. Cause: {}", id, e.getMessage(), e);
            throw PostgresErrorHandler.mapToHttpException(e);
        }
    }
    
    public List<Tutorial> findAll() {
        try {
            return this.tutorialRepository.findAll();
        } catch (DataAccessException e) {
            logger.error("Failed to find all tutorials");
            throw PostgresErrorHandler.mapToHttpException(e);
        }
    }
    
    public void delete(UUID id) {
        try {
            Tutorial existingTutorial = this.tutorialRepository.findById(id);
            if (existingTutorial == null) throw new ResourceNotFoundException(
                    String.format("Tutorial with id: %s not found", id)
            );
            this.tutorialRepository.deleteById(id);
        } catch (DataAccessException e) {
            logger.error("Failed to delete tutorial by id: {}", id);
            throw PostgresErrorHandler.mapToHttpException(e);
        }
    }
}
