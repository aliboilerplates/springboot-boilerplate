package com.example.demo.mapper;

import com.example.demo.dto.CreateTutorialDto;
import com.example.demo.dto.UpdateTutorialDto;
import com.example.demo.model.Tutorial;
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
