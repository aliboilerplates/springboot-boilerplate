package com.example.demo.mapper;

import com.example.demo.dto.CreateTutorialDto;
import com.example.demo.model.Tutorial;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TutorialMapper {
    
    public Tutorial toEntity(CreateTutorialDto createTutorialDto);
}
