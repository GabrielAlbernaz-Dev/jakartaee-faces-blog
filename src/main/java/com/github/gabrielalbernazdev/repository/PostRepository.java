package com.github.gabrielalbernazdev.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.github.gabrielalbernazdev.domain.model.Post;

public interface PostRepository {
    List<Post> findAll();
    List<Post> findAllActive();
    Optional<Post> findById(UUID id);
    Optional<Post> findByTitle(String title);
    Optional<Post> findByDescription(String description);
    void save(Post post);
    void delete(Post post);
}
