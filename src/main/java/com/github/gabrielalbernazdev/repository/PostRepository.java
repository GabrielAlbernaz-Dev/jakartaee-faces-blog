package com.github.gabrielalbernazdev.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.github.gabrielalbernazdev.domain.model.Post;
import com.github.gabrielalbernazdev.domain.model.User;

public interface PostRepository {
    List<Post> findAllByUser(User user);
    List<Post> findAllActiveByUser(User user);
    Optional<Post> findById(UUID id);
    Optional<Post> findByTitle(String title);
    Optional<Post> findByDescription(String description);
    void save(Post post);
    void delete(Post post);
} 
