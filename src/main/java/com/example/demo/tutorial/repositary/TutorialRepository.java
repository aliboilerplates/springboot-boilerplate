package com.example.demo.tutorial.repositary;

import com.example.demo.tutorial.entity.Tutorial;
import org.springframework.data.repository.ListCrudRepository;


import java.util.List;
import java.util.UUID;

public interface TutorialRepository extends ListCrudRepository<Tutorial, UUID> {
    List<Tutorial> findByTitle(String title);
}

