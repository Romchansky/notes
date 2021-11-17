package com.goit.notes.service;

import com.goit.notes.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NoteService extends BaseService<Note, UUID> {

    public NoteService( JpaRepository<Note, UUID> repository){
        super(repository);  }

    public Note createNote(Note note)
    {
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
