package com.emilio.backend.mappers;

import com.emilio.backend.dtos.request.NoteRequestDto;
import com.emilio.backend.dtos.response.NoteResponseDto;
import com.emilio.backend.entities.NoteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
    uses = CategoryMapper.class,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface NoteMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "body", source = "body")
    @Mapping(target = "archived", source = "archived")
    NoteResponseDto toResponse(NoteEntity noteEntity);
    @Mapping(target = "body", source = "body")
    NoteEntity toEntity(NoteRequestDto noteRequestDto);
}
