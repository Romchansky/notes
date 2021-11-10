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
        //todo;
    }
    public Note shareNote(Note note){
        //todo;
    }

    public Note editNote(Note note) {
        //todo
    }

}
