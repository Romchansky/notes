package com.goit.notes.service;


import com.goit.notes.entity.Access;
import com.goit.notes.entity.Note;
import com.goit.notes.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@Slf4j
public class NoteService extends BaseService<Note, UUID> {

    private final NoteRepository repository;

    public NoteService(NoteRepository repository){
        super(repository);
        this.repository = repository;

    }

    public Note createNote(Note note) {

        return repository.save(note);
    }
    public Note shareNote(UUID id){
        if (repository.existsById(id)) {
            Note note = repository.getById(id);
            if (note.getAccess().equals(Access.PUBLIC)) {
                return note;
            } else {
                    throw new RuntimeException("Note is private");
            }
        }
            throw new RuntimeException("note with such id doesn`t exists");
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
