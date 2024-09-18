package com.emilio.backend.dtos.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteResponseDto {
    private Long id;
    private String body;
    private boolean archived;
    private CategoryResponseDto category;
}
