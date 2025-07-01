package com.example.demo.repositary;

import com.example.demo.model.Tutorial;
import org.springframework.data.repository.ListCrudRepository;


import java.util.UUID;

public interface TutorialRepository extends ListCrudRepository<Tutorial, UUID> {

}
