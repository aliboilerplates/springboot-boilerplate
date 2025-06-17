package com.example.demo.controller;

import com.example.demo.dto.CreateTutorialDto;
import com.example.demo.payload.ApiResponse;
import com.example.demo.service.TutorialService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/v1")
public class TutorialController {
    
    private final TutorialService tutorialService;
    
    TutorialController(TutorialService tutorialService) {
        this.tutorialService = tutorialService;
    }
    
    
    @GetMapping("tutorials")
    public ApiResponse<String> getAllTutorials() {
        var res = new ApiResponse<String>();
        res.setMessage("Tutorials Retrieved");
        res.setStatusCode(HttpStatus.OK);
        res.setData("Tutorial 1");
        return res;
    }
    
    
    @PostMapping("tutorials")
    public ResponseEntity<String> createTutorial(
            @Valid @RequestBody CreateTutorialDto createTutorialDto
    ) {
        this.tutorialService.create(createTutorialDto);
        return new ResponseEntity<>("Tutorial Created", HttpStatus.CREATED);
    }
}
