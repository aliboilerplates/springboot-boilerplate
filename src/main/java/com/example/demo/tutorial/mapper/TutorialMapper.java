package com.example.demo.tutorial.mapper;

import com.example.demo.tutorial.dto.CreateTutorialDto;
import com.example.demo.tutorial.dto.UpdateTutorialDto;
import com.example.demo.tutorial.entity.Tutorial;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TutorialMapper {
    
    TutorialMapper INSTANCE = Mappers.getMapper(TutorialMapper.class);
    
    Tutorial toEntity(CreateTutorialDto createTutorialDto);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UpdateTutorialDto updateTutorialDto, @MappingTarget Tutorial entity);
    
}
