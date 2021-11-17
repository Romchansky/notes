package com.goit.notes.service;

import com.goit.notes.entity.Note;
import com.goit.notes.repository.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class NoteService extends BaseService<Note, UUID> {

    public NoteService(NoteRepository repository) {
        super(repository);
    }

}
