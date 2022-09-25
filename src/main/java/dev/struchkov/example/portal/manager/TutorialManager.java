package dev.struchkov.example.portal.manager;

import dev.struchkov.example.portal.model.Comment;
import dev.struchkov.example.portal.model.Tag;
import dev.struchkov.example.portal.model.Tutorial;
import dev.struchkov.example.portal.service.CommentService;
import dev.struchkov.example.portal.service.TagService;
import dev.struchkov.example.portal.service.TutorialService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TutorialManager {

    private final TutorialService tutorialService;
    private final CommentService commentService;
    private final TagService tagService;

    @Transactional
    public void addTagToTutorial(Long tutorialId, Tag tag) {
        final Tutorial tutorial = tutorialService.getByIdOrThrow(tutorialId);
        final Tag newTag = getOrCreateTag(tag);
        tutorial.addTag(newTag);
    }

    @Transactional
    public Comment addNewComment(@NonNull Long tutorialId, @NonNull Comment newComment) {
        final Tutorial tutorial = tutorialService.getByIdOrThrow(tutorialId);
        newComment.setTutorial(tutorial);
        return commentService.create(newComment);
    }

    private Tag getOrCreateTag(Tag newTag) {
        if (newTag.getId() != null) {
            return tagService.getByIdOrThrow(newTag.getId());
        } else {
            return tagService.create(newTag);
        }
    }

}
