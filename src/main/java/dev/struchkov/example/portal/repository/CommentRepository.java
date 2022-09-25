package dev.struchkov.example.portal.repository;

import dev.struchkov.example.portal.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByTutorialId(Long postId);

    void deleteByTutorialId(Long tutorialId);

}
