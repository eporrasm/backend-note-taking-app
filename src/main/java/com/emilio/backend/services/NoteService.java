package com.emilio.backend.services;

import com.emilio.backend.dtos.request.NoteRequestDto;
import com.emilio.backend.dtos.response.NoteResponseDto;
import com.emilio.backend.entities.CategoryEntity;
import com.emilio.backend.entities.NoteEntity;
import com.emilio.backend.mappers.NoteMapper;
import com.emilio.backend.repositories.CategoryRepository;
import com.emilio.backend.repositories.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final CategoryRepository categoryRepository;
    private final NoteMapper noteMapper;

    public NoteResponseDto create(NoteRequestDto noteRequestDto) {
        return noteMapper.toResponse(
                noteRepository.save(
                        noteMapper.toEntity(noteRequestDto)));

    }

    public NoteResponseDto update(Long id, NoteRequestDto noteRequestDto) {
        NoteEntity noteEntity = noteRepository.findById(id).orElseThrow(RuntimeException::new);
        if (noteRequestDto.getBody() != null) {
            noteEntity.setBody(noteRequestDto.getBody());
        }
        if (noteRequestDto.getArchived() != null) {
            noteEntity.setArchived(noteRequestDto.getArchived());
        }
        if (noteRequestDto.getCategoryId() != null) {
            CategoryEntity category = categoryRepository.findById(noteRequestDto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            noteEntity.setCategory(category);
        } else {
            noteEntity.setCategory(null);
        }
        return noteMapper.toResponse(noteRepository.save(noteEntity));
    }

    public void delete(Long id) {
        noteRepository.deleteById(id);
    }

    public List<NoteResponseDto> listNotesByArchivedAndCategory(boolean archived, Long categoryId) {
        List<NoteEntity> notes;
        if (categoryId != null) {
            notes = noteRepository.findByArchivedAndCategoryId(archived, categoryId);
        } else {
            notes = noteRepository.findByArchived(archived);
        }
        return notes.stream()
                .map(noteMapper::toResponse)
                .collect(Collectors.toList());
    }
}
