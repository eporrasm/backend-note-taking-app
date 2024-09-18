package com.emilio.backend.mappers;

import com.emilio.backend.dtos.request.CategoryRequestDto;
import com.emilio.backend.dtos.response.CategoryResponseDto;
import com.emilio.backend.entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    CategoryResponseDto toResponse(CategoryEntity categoryEntity);
    @Mapping(target = "name", source = "name")
    CategoryEntity toEntity(CategoryRequestDto categoryRequestDto);
}
