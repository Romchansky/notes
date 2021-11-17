package com.goit.notes.repository;

import com.goit.notes.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);

}