package com.example.demo.controller;

import com.example.demo.dto.CreateTutorialDto;
import com.example.demo.model.Tutorial;
import com.example.demo.payload.ApiResponse;
import com.example.demo.service.TutorialService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/v1")
public class TutorialController {
    
    private final TutorialService tutorialService;
    
    TutorialController(TutorialService tutorialService) {
        this.tutorialService = tutorialService;
    }
    
    
    @GetMapping("tutorials")
    public ApiResponse<List<Tutorial>> getAllTutorials() {
        List<Tutorial> tutorials = this.tutorialService.findAll();
        var res = new ApiResponse<List<Tutorial>>();
        res.setMessage("Tutorials Retrieved");
        res.setStatusCode(HttpStatus.OK);
        res.setData(tutorials);
        return res;
    }
    
    
    @PostMapping("tutorials")
    public ApiResponse<Object> createTutorial(
            @Valid @RequestBody CreateTutorialDto createTutorialDto
    ) {
        this.tutorialService.create(createTutorialDto);
        return new ApiResponse<>("tutorial created", HttpStatus.OK);
    }
}
