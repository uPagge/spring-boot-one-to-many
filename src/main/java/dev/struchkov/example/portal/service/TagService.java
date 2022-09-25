package dev.struchkov.example.portal.service;

import dev.struchkov.example.portal.exception.NotFoundException;
import dev.struchkov.example.portal.model.Tag;
import dev.struchkov.example.portal.repository.TagRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository repository;

    public Tag create(@NonNull Tag newTag) {
        return repository.save(newTag);
    }

    public Tag update(@NonNull Long tagId, @NonNull Tag newTag) {
        final Tag oldTag = repository.findById(tagId)
                .orElseThrow(() -> new NotFoundException("Not found Tag with id = " + tagId));

        oldTag.setName(newTag.getName());

        return oldTag;
    }

    public List<Tag> getAll() {
        return repository.findAll();
    }

    public List<Tag> getAllByTutorialId(@NonNull Long tutorialId) {
        return repository.findTagsByTutorialsId(tutorialId);
    }

    public Tag getByIdOrThrow(@NonNull Long tagId) {
        return repository.findById(tagId)
                .orElseThrow(() -> new NotFoundException("Not found Tag with id = " + tagId));
    }

    public void deleteById(@NonNull Long tagId) {
        repository.deleteById(tagId);
    }

}
