package com.example.demo.controller;

import com.example.demo.dto.CreateTutorialDto;
import com.example.demo.dto.UpdateTutorialDto;
import com.example.demo.model.Tutorial;
import com.example.demo.payload.ApiResponseBody;
import com.example.demo.service.TutorialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/v1/tutorials")
@Tag(name = "Tutorials", description = "Tutorial management APIs")
public class TutorialController {
    
    private final TutorialService tutorialService;
    
    TutorialController(TutorialService tutorialService) {
        this.tutorialService = tutorialService;
    }
    
    
    @GetMapping()
    @Operation(summary = "Get all tutorials", description = "Returns list of all tutorials")
    public ApiResponseBody<List<Tutorial>> getAllTutorials() {
        List<Tutorial> tutorials = this.tutorialService.findAll();
        var res = new ApiResponseBody<List<Tutorial>>();
        res.setMessage("Tutorials Retrieved");
        res.setStatusCode(HttpStatus.OK);
        res.setData(tutorials);
        return res;
    }
    
    
    @PostMapping()
    @Operation(summary = "Create a new tutorial", description = "Creates a new tutorial")
    @ApiResponse(description = "Returns the created tutorial", responseCode = "201")
    public ApiResponseBody<Tutorial> createTutorial(
            @Valid @RequestBody CreateTutorialDto createTutorialDto
    ) {
        Tutorial newTutorial = this.tutorialService.create(createTutorialDto);
        return new ApiResponseBody<>(newTutorial);
    }
    
    @PatchMapping("/{id}")
    @Operation(summary = "Update existing tutorial")
    @ApiResponse(description = "Returns the updated tutorial", responseCode = "200")
    public ApiResponseBody<Tutorial> updateTutorial(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateTutorialDto updateTutorialDto
    ) {
        Tutorial updatedTutorial = this.tutorialService.update(id, updateTutorialDto);
        return new ApiResponseBody<>(updatedTutorial);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes existing record")
    public ApiResponseBody<Void> deleteTutorial(
            @PathVariable UUID id
    ) {
        this.tutorialService.delete(id);
        return new ApiResponseBody<>(
                "Tutorial deleted successfully",
                HttpStatus.OK
        );
    }
    
    
}
