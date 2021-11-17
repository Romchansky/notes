package com.goit.notes.entity.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class NoteDto {

    private final UUID id;
    private final String title;
    private final String content;
    private final String accessType;
    private final String userName;

}
