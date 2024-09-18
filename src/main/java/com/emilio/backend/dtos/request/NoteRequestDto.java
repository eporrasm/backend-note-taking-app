package com.emilio.backend.dtos.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteRequestDto {
    private String body;
    private Boolean archived;
    private Long categoryId;
}
