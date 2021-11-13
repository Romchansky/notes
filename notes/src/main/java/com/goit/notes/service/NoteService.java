package com.goit.notes.service;


import com.goit.notes.entity.Access;
import com.goit.notes.entity.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class NoteService extends BaseService<Note, UUID> {

    private JpaRepository<Note, UUID> repository;

    public NoteService(JpaRepository<Note, UUID> repository) {
        super(repository);
    }


    public Note createNote(Note note) {
        return repository.save(note);
    }

    public Note shareNote(UUID id) {
        if (repository.existsById(id)) {
            Note note = repository.getById(id);
            if (note.getAccess().equals(Access.PUBLIC)) {
                return note;
            } else {
                throw new RuntimeException("Note is private");
            }
        }
        throw new RuntimeException("note with such id does`t exists");
    }

    public Note editNote(Note note) {
        return createNote(note);
    }
}
/*
* проверка на ограничение длины заметки
* поделиться заметкой должна вернуться ссылка на неё
* редактирование ноты, каким образом она рекдактируется? просто перезапись происходит или что-то иначе
*
* */