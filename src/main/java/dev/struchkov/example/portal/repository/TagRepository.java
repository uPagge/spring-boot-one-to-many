package dev.struchkov.example.portal.repository;

import dev.struchkov.example.portal.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findTagsByTutorialsId(Long tutorialId);

}
