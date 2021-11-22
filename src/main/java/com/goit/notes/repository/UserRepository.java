package com.goit.notes.repository;

import com.goit.notes.entity.NoteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<NoteUser, UUID> {

    Optional<NoteUser> findByUserName(String userName);

    boolean existsByUserName(String userName);
}
