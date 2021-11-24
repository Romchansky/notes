package com.goit.notes.service;

import com.goit.notes.entity.NoteUser;
import com.goit.notes.entity.Role;
import com.goit.notes.exception.ImpossibleActionException;
import com.goit.notes.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService extends BaseService<NoteUser, UUID> {

    private final UserRepository repository;

    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository repository, BCryptPasswordEncoder encoder) {
        super(repository);
        this.repository = repository;
        this.encoder = encoder;
    }

    public void register(NoteUser noteUser) {
        if (repository.existsByUserName(noteUser.getUserName()))
            throw new ImpossibleActionException("Account with username '" + noteUser.getUserName() + "' already exists");
        noteUser.setPassword(encoder.encode(noteUser.getPassword()));
        noteUser.setUserRole(Role.ROLE_USER);
        repository.save (noteUser);
    }

    public Optional<NoteUser> findByName(String name){
       return repository.findByUserName(name);
    }
}
