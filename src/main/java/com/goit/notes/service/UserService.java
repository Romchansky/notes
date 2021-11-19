package com.goit.notes.service;

import com.goit.notes.entity.Note;
import com.goit.notes.entity.Role;
import com.goit.notes.entity.User;
import com.goit.notes.exception.ImpossibleActionException;
import com.goit.notes.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
public class UserService extends BaseService<User, UUID> {

    private final UserRepository repository;

    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository repository, BCryptPasswordEncoder encoder) {
        super(repository);
        this.repository = repository;
        this.encoder = encoder;
    }

    public User register(User user) {
        if (repository.existsByUserName(user.getUserName())) 
            throw new ImpossibleActionException("Account with username '" + user.getUserName() + "' already exists");
        user.setPassword(encoder.encode(user.getPassword()));
        user.setUserRole(Role.ROLE_USER);
        return repository.save(user);
    }

    public Optional<User> findByName(String name){
       return repository.findByUserName(name);
    }
}
