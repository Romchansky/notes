package com.goit.notes.service;


import com.goit.notes.entity.Access;
import com.goit.notes.entity.Note;
import com.goit.notes.repository.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@Slf4j
public class NoteService extends BaseService<Note, UUID> {

    private final NoteRepository repository;

    public NoteService(NoteRepository repository) {
        super(repository);
        this.repository = repository;

    }

    public Note create(Note note) {
        if (note.getDescription().length() > 10000) {
            log.info("The length note can't be more than 10_000 symbols");
        }
        return repository.save(note);

    }

    public String share(Note note) {
        if (note.getAccess().equals(Access.PRIVATE)) {
            log.info("You can't share this note, because this note has a private access type");
        }
        String url = "/note/share/" + note.getId().toString();
        return url;
    }

    public Note edit(Note note) {
        return create(note);
    }
}
