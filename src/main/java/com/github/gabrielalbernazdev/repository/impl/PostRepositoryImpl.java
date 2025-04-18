package com.github.gabrielalbernazdev.repository.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.github.gabrielalbernazdev.domain.model.Post;
import com.github.gabrielalbernazdev.repository.PostRepository;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Stateless
public class PostRepositoryImpl implements PostRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Post> findAll() {
        String jpql = "SELECT p FROM Post p";
        return entityManager.createQuery(jpql, Post.class)
                .getResultList();
    }

    @Override
    public List<Post> findAllActive() {
        String jpql = "SELECT p FROM Post p WHERE p.deletedAt IS NULL";
        return entityManager.createQuery(jpql, Post.class)
                .getResultList();
    }

    @Override
    public Optional<Post> findById(UUID id) {
        return Optional.ofNullable(entityManager.find(Post.class, id));
    }

    @Override
    public Optional<Post> findByTitle(String title) {
        String jpql = "SELECT p FROM Post p WHERE p.title = :title";
        return entityManager.createQuery(jpql, Post.class)
                .setParameter("title", title)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Optional<Post> findByDescription(String description) {
        String jqpl = "SELECT p FROM Post p WHERE p.description = :description";
        return entityManager.createQuery(jqpl, Post.class)
                .setParameter("description", description)
                .getResultStream()
                .findFirst();
    }

    @Override
    @Transactional
    public void save(Post post) {
        if (post.getId() == null) {
            entityManager.persist(post);
        } else {
            entityManager.merge(post);
        }
    }

    @Override
    @Transactional
    public void delete(Post post) {
        post.setDeletedAt(LocalDateTime.now());
    }
}
