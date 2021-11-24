package com.goit.notes.service;

import com.goit.notes.entity.BaseEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public abstract class BaseService<E extends BaseEntity<ID>, ID> {

    private final JpaRepository<E, ID> repository;

    public void save(E entity) {
        repository.save (entity);
    }

    public List<E> findAll() {
        return repository.findAll();
    }

    public Optional<E> findById(ID id) {
        return repository.findById(id);
    }
    
    public E getById(ID id) {
        return findById(id).orElseThrow(()->new RuntimeException("Entity with id " + id + " not exist"));
    }
    
    public void delete(ID id) {
        repository.deleteById (id);
    }

}
