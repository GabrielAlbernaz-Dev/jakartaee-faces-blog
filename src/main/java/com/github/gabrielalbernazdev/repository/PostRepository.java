package com.github.gabrielalbernazdev.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.github.gabrielalbernazdev.domain.model.Post;

public interface PostRepository {
    List<Post> findAllByUser(UUID userId);
    List<Post> findAllActiveByUser(UUID userId);
    Optional<Post> findById(UUID id);
    Optional<Post> findByTitle(String title);
    List<Post> findAllMostRecentByUser(UUID userId, int limit);
    void save(Post post);
    void delete(Post post);
} 
