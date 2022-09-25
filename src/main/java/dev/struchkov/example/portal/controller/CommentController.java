package dev.struchkov.example.portal.controller;

import dev.struchkov.example.portal.manager.TutorialManager;
import dev.struchkov.example.portal.model.Comment;
import dev.struchkov.example.portal.service.CommentService;
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
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final TutorialManager tutorialManager;

    private final CommentService commentService;

    @GetMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsByTutorialId(
            @PathVariable(value = "tutorialId") Long tutorialId
    ) {
        return ResponseEntity.ok(commentService.getByTutorialId(tutorialId));
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<Comment> getCommentsByTutorialId(
            @PathVariable(value = "commentId") Long commentId
    ) {
        return ResponseEntity.ok(commentService.getByIdOrThrow(commentId));
    }

    @PostMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<Comment> createComment(
            @PathVariable(value = "tutorialId") Long tutorialId,
            @RequestBody Comment newComment
    ) {
        return ResponseEntity.ok(tutorialManager.addNewComment(tutorialId, newComment));
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(
            @PathVariable("commentId") Long id,
            @RequestBody Comment updateComment
    ) {
        return ResponseEntity.ok(commentService.update(id, updateComment));
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<HttpStatus> deleteComment(
            @PathVariable("commentId") Long commentId
    ) {
        commentService.deleteById(commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<List<Comment>> deleteAllCommentsOfTutorial(
            @PathVariable(value = "tutorialId") Long tutorialId
    ) {
        commentService.deleteAllByTutorialId(tutorialId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
