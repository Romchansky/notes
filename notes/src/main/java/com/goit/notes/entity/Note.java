package com.goit.notes.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Note implements BaseEntity {
//
//    public Note(JpaRepository<Note, UUID> repository) {
//        super(repository);
//    }
//
//    public Note createNote(Note note)
//    {
//        return repository.save(note);
//    }
//    public Note shareNote(UUID id){
//        if (repository.existsById(id)) {
//            Note note = repository.getById(id);
//            if (note.getAccess().equals(Access.PUBLIC)) {
//                return note;
//            } else {
//                throw new RuntimeException("Note is private");
//            }
//        }
//        throw new RuntimeException("note with such id doesn`t exists");
//    }
//
//    public Note editNote(Note note) {
//        return createNote(note);
//    }

    @Override
    public User loadUserByUsername(String userName) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public boolean isAdmin() {
        return false;
    }

    @Override
    public Long getId() {

        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public void setPassword(String password) {

    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void setActive(boolean active) {

    }
    @Override
    public Object getAccess() {
        return null;
    }

    @Override
    public void setUsername(String userName) {

    }
}