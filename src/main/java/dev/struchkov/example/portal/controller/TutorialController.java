package dev.struchkov.example.portal.controller;

import dev.struchkov.example.portal.model.Tutorial;
import dev.struchkov.example.portal.service.TutorialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8081")
public class TutorialController {

    private final TutorialService tutorialService;

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(
            @RequestParam(required = false) String title
    ) {
        final List<Tutorial> tutorials;
        if (title == null) {
            tutorials = tutorialService.getAll();
        } else {
            tutorials = tutorialService.findByTitle(title);
        }

        if (tutorials.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(tutorials);
    }

    @GetMapping("/tutorials/{tutorialId}")
    public ResponseEntity<Tutorial> getTutorialById(
            @PathVariable("tutorialId") Long tutorialId
    ) {
        return ResponseEntity.ok(tutorialService.getByIdOrThrow(tutorialId));
    }

    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(
            @RequestBody Tutorial tutorial
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tutorialService.create(tutorial));
    }

    @PutMapping("/tutorials/{tutorialId}")
    public ResponseEntity<Tutorial> updateTutorial(
            @PathVariable("tutorialId") Long tutorialId,
            @RequestBody Tutorial newTutorial
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tutorialService.update(tutorialId, newTutorial));
    }

    @DeleteMapping("/tutorials/{tutorialId}")
    public ResponseEntity<HttpStatus> deleteTutorial(
            @PathVariable("tutorialId") Long tutorialId
    ) {
        tutorialService.deleteById(tutorialId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        tutorialService.deleteAll();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<List<Tutorial>> findByPublished() {
        final List<Tutorial> tutorials = tutorialService.findByPublished(true);

        if (tutorials.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(tutorials);
    }
}
