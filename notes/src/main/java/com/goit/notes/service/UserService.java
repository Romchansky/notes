package com.goit.notes.service;

import com.goit.notes.entity.User;
import com.goit.notes.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service

public class UserService extends BaseService<User, UUID> {

    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository repository, BCryptPasswordEncoder encoder) {
        super(repository);
        this.repository = repository;
        this.encoder = encoder;
    }


    public void register(User user) {
        //todo
    }

    @Override
    public User save(User user) {
        //todo
        return repository.save(user);
    }
}
