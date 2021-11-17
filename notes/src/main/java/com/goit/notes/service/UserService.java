package com.goit.notes.service;

import com.goit.notes.repository.UserRepository;
import com.goit.notes.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public boolean addUser(User user) {
        User userFromDb = userRepository.findByUserName(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        //user.setActive(true);
       // user.setRoles(Collections.singleton(Role.USER));
       // user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return true;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void saveUser(User user, String userName, Map<String, String> form) {
        user.getUsername();

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        //user.getRoles().clear();

//        for (String key : form.keySet()) {
//            if (roles.contains(key)) {
//                user.getRoles().add(Role.valueOf(key));
//            }
//        }

        userRepository.save(user);
    }

    public void updateProfile(User user, String password) {
        String userPassword = user.getPassword();

        boolean isPasswordChanged = (password != null && !password.equals(userPassword)) ||
                (userPassword != null && !userPassword.equals(password));

//        if (isPasswordChanged) {
//            user.setPassword(password);
//
//        }
//
//        if (!StringUtils.isEmpty(password)) {
//            user.setPassword(password);
//        }

        userRepository.save(user);
    }

    public void registrationUser(User user, BindingResult result) {

    }
}