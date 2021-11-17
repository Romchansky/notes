package com.goit.notes.service;

import com.goit.notes.entity.Role;
import com.goit.notes.entity.User;
import com.goit.notes.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
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

    public void register(User user) {
        if (repository.existsByUserName(user.getUserName ())) {
            log.warn ("Account with provided username already exists");
        }
        user.setUserRole(Role.ROLE_USER);
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
    }

    @Override
    public User save(User user) {
        if (repository.existsByUserName (user.getUserName ())) {
            log.warn (String.format ("User with specified username [%s] already exists", user.getUserName ()));
        }
        user.setUserRole (user.getUserRole ());

        user.setPassword(encoder.encode(user.getPassword()));
         return repository.save (user);
    }
}

