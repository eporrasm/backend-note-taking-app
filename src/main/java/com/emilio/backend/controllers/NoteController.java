package com.emilio.backend.controllers;

import com.emilio.backend.dtos.request.NoteRequestDto;
import com.emilio.backend.dtos.response.NoteResponseDto;
import com.emilio.backend.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/notes")
public class NoteController {
    private final NoteService noteService;

    @PostMapping
    public ResponseEntity<NoteResponseDto> create(
            @RequestBody NoteRequestDto noteRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.create(noteRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponseDto> update(
            @PathVariable Long id,
            @RequestBody NoteRequestDto noteRequestDto) {
        return ResponseEntity.ok(noteService.update(id, noteRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {
        noteService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<NoteResponseDto>> listNotes(
            @RequestParam(required = false) Boolean archived,
            @RequestParam(required = false) Long categoryId) {
        if (archived == null) {
            archived = false;
        }
        return ResponseEntity.ok(noteService.listNotesByArchivedAndCategory(archived, categoryId));
    }
}
