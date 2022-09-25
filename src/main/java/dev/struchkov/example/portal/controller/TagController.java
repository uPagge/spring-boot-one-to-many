package dev.struchkov.example.portal.controller;

import dev.struchkov.example.portal.manager.TutorialManager;
import dev.struchkov.example.portal.model.Tag;
import dev.struchkov.example.portal.model.Tutorial;
import dev.struchkov.example.portal.service.TagService;
import dev.struchkov.example.portal.service.TutorialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TagController {

    private final TutorialManager tutorialManager;

    private final TutorialService tutorialService;
    private final TagService tagService;

    @GetMapping("/tags")
    public ResponseEntity<List<Tag>> getAllTags() {
        final List<Tag> tags = tagService.getAll();
        if (tags.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(tags);
    }

    @GetMapping("/tutorials/{tutorialId}/tags")
    public ResponseEntity<List<Tag>> getAllTagsByTutorialId(@PathVariable(value = "tutorialId") Long tutorialId) {
        final List<Tag> tags = tagService.getAllByTutorialId(tutorialId);

        if (tags.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(tags);
    }

    @GetMapping("/tags/{tagId}")
    public ResponseEntity<Tag> getTagsById(@PathVariable(value = "tagId") Long tagId) {
        return ResponseEntity.ok(tagService.getByIdOrThrow(tagId));
    }

    @GetMapping("/tags/{tagId}/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorialsByTagId(@PathVariable(value = "tagId") Long tagId) {
        final List<Tutorial> tutorials = tutorialService.getAllByTagId(tagId);

        if (tutorials.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.ok(tutorials);
    }

    @PostMapping("/tutorials/{tutorialId}/tags")
    public ResponseEntity<Tag> addTagToTutorial(@PathVariable(value = "tutorialId") Long tutorialId, @RequestBody Tag tag) {
        tutorialManager.addTagToTutorial(tutorialId, tag);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/tags/{tagId}")
    public ResponseEntity<Tag> updateTag(@PathVariable("tagId") Long tagId, @RequestBody Tag newTag) {
        return ResponseEntity.ok(tagService.update(tagId, newTag));
    }

    @DeleteMapping("/tutorials/{tutorialId}/tags/{tagId}")
    public ResponseEntity<HttpStatus> deleteTagFromTutorial(
            @PathVariable(value = "tutorialId") Long tutorialId,
            @PathVariable(value = "tagId") Long tagId
    ) {
        tutorialService.deleteTagFromTutorial(tutorialId, tagId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/tags/{tagId}")
    public ResponseEntity<HttpStatus> deleteTag(@PathVariable("tagId") Long tagId) {
        tagService.deleteById(tagId);
        return ResponseEntity.ok().build();
    }
}
