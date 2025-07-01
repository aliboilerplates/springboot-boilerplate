package com.example.demo.tutorial.repositary;

import com.example.demo.tutorial.model.Tutorial;
import org.springframework.data.repository.ListCrudRepository;


import java.util.UUID;

public interface TutorialRepository extends ListCrudRepository<Tutorial, UUID> {

}
