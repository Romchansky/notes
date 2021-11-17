package com.goit.notes.service;

import com.goit.notes.entity.BaseEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public abstract class BaseService<E extends BaseEntity<ID>, ID> {


    private final JpaRepository<E, ID> repository;

    public E save (E entity){ return repository.save(entity); }

    public Iterable<E> findAll(){ return repository.findAll(); }

    public Optional<E> findById(ID id){ return repository.findById(id); }

    public void delete(E e){ repository.delete (e); }

}

