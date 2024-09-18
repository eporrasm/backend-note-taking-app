package com.emilio.backend.services;

import com.emilio.backend.dtos.request.CategoryRequestDto;
import com.emilio.backend.dtos.response.CategoryResponseDto;
import com.emilio.backend.mappers.CategoryMapper;
import com.emilio.backend.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryResponseDto create(CategoryRequestDto categoryRequestDto) {
        return categoryMapper.toResponse(
                categoryRepository.save(
                        categoryMapper.toEntity(categoryRequestDto)));
    }

    public List<CategoryResponseDto> getAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toResponse)
                .collect(Collectors.toList());
    }
}
