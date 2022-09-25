package dev.struchkov.example.portal.service;

import dev.struchkov.example.portal.exception.NotFoundException;
import dev.struchkov.example.portal.model.Comment;
import dev.struchkov.example.portal.repository.CommentRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repository;

    public Comment create(@NonNull Comment newComment) {
        newComment.setId(null);
        return repository.save(newComment);
    }

    public Comment update(@NonNull Long commentId, @NonNull Comment updateComment) {
        final Comment comment = repository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Not found Comment with id = " + commentId));

        comment.setContent(updateComment.getContent());

        return comment;
    }

    public List<Comment> getByTutorialId(@NonNull Long tutorialId) {
        return repository.findByTutorialId(tutorialId);
    }

    public Comment getByIdOrThrow(@NonNull Long commentId) {
        return repository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Not found Comment with id = " + commentId));
    }

    public void deleteById(@NonNull Long commentId) {
        repository.deleteById(commentId);
    }

    public void deleteAllByTutorialId(@NonNull Long tutorialId) {
        repository.deleteByTutorialId(tutorialId);
    }

}
