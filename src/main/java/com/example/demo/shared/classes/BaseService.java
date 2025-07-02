package com.example.demo.shared.classes;

import com.example.demo.shared.exception.ResourceNotFoundException;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T, ID> {
    
    protected final ListCrudRepository<T, ID> repository;
    
    protected BaseService(ListCrudRepository<T, ID> repository) {
        this.repository = repository;
    }
    
    
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }
    
    public List<T> findAll() {
        return repository.findAll();
    }
    
    public T update(ID id, T updatedEntity) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Entity not found");
        }
        return repository.save(updatedEntity);
    }
    
    public void delete(ID id) {
        repository.deleteById(id);
    }
}

