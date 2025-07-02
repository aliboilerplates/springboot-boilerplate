package com.example.demo.tutorial.service;

import com.example.demo.tutorial.dto.CreateTutorialDto;
import com.example.demo.tutorial.dto.UpdateTutorialDto;
import com.example.demo.shared.exception.PostgresErrorHandler;
import com.example.demo.shared.exception.PostgresErrorMessage;
import com.example.demo.shared.exception.ResourceNotFoundException;
import com.example.demo.tutorial.mapper.TutorialMapper;
import com.example.demo.tutorial.entity.Tutorial;
import com.example.demo.tutorial.repositary.TutorialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        } catch (DbActionExecutionException e) {
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
            Optional<Tutorial> existingTutorial = this.tutorialRepository.findById(id);
            
            if (existingTutorial.isEmpty())
                throw new ResourceNotFoundException("Tutorial not Found");
            
            this.tutorialMapper.updateEntityFromDto(updateTutorialDto, existingTutorial.get());
            
            return tutorialRepository.save(existingTutorial.get());
        } catch (DbActionExecutionException e) {
            logger.error("Failed to update tutorial with id: {}. Cause: {}", id, e.getMessage(), e);
            throw PostgresErrorHandler.mapToHttpException(e);
        }
    }
    
    public List<Tutorial> findAll() {
        try {
            return this.tutorialRepository.findAll();
        } catch (DbActionExecutionException e) {
            logger.error("Failed to find all tutorials");
            throw PostgresErrorHandler.mapToHttpException(e);
        }
    }
    
    public void delete(UUID id) {
        try {
            Optional<Tutorial> existingTutorial = this.tutorialRepository.findById(id);
            if (existingTutorial.isEmpty()) throw new ResourceNotFoundException(
                    String.format("Tutorial with id: %s not found", id)
            );
            this.tutorialRepository.deleteById(id);
        } catch (DbActionExecutionException e) {
            logger.error("Failed to delete tutorial by id: {}", id);
            throw PostgresErrorHandler.mapToHttpException(e);
        }
    }
    
    
}
