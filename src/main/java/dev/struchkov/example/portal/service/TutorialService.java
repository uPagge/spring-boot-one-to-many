package dev.struchkov.example.portal.service;

import dev.struchkov.example.portal.exception.NotFoundException;
import dev.struchkov.example.portal.model.Tutorial;
import dev.struchkov.example.portal.repository.TutorialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TutorialService {

    private final TutorialRepository repository;

    public Tutorial getByIdOrThrow(Long tutorialId) {
        return repository.findById(tutorialId)
                .orElseThrow(() -> new NotFoundException("Not found Tutorial with id = " + tutorialId));
    }

    public List<Tutorial> getAll() {
        return repository.findAll();
    }

    public List<Tutorial> findByTitle(String title) {
        return repository.findByTitleContaining(title);
    }

    public Tutorial create(Tutorial tutorial) {
        tutorial.setId(null);
        tutorial.setPublished(true);
        return repository.save(tutorial);
    }

    public Tutorial update(Long tutorialId, Tutorial newTutorial) {
        final Tutorial oldTutorial = repository.findById(tutorialId)
                .orElseThrow(() -> new NotFoundException("Not found Tutorial with id = " + tutorialId));

        oldTutorial.setDescription(newTutorial.getDescription());
        oldTutorial.setTitle(newTutorial.getTitle());
        oldTutorial.setPublished(newTutorial.isPublished());

        return oldTutorial;
    }

    public void deleteById(Long tutorialId) {
        repository.deleteById(tutorialId);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public List<Tutorial> findByPublished(boolean published) {
        return repository.findByPublished(published);
    }

    public List<Tutorial> getAllByTagId(Long tagId) {
        return repository.findTutorialsByTagsId(tagId);
    }

    public void deleteTagFromTutorial(Long tutorialId, Long tagId) {
        repository.findById(tutorialId)
                .orElseThrow(() -> new NotFoundException("Not found Tutorial with id = " + tutorialId))
                .removeTag(tagId);
    }

}
