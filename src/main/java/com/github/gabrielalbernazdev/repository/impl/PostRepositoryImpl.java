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
import jakarta.persistence.TypedQuery;

@Stateless
public class PostRepositoryImpl implements PostRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Post> findAllByUser(UUID userId) {
        TypedQuery<Post> q = em.createNamedQuery("Post.findAllByUser", Post.class);
        return q.setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Post> findAllActiveByUser(UUID userId) {
        TypedQuery<Post> q = em.createNamedQuery("Post.findAllActiveByUser", Post.class);
        return q.setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public Optional<Post> findById(UUID id) {
        return Optional.ofNullable(em.find(Post.class, id));
    }

    @Override
    public List<Post> findAllMostRecentByUser(UUID userId, int limit) {
        TypedQuery<Post> q = em.createNamedQuery("Post.findMostRecentByUser", Post.class);
        return q.setParameter("userId", userId)
                .setMaxResults(limit) 
                .getResultList();
    }

    @Override
    public Optional<Post> findByTitle(String title) {
        TypedQuery<Post> q = em.createNamedQuery("Post.findByTitle", Post.class);
        return q.setParameter("title", title)
                .getResultStream()
                .findFirst();
    }

    @Override
    public void save(Post post) {
        if (post.getId() == null) {
            em.persist(post);
        } else {
            em.merge(post);
        }
    }

    @Override
    public void delete(Post post) {
        post.setDeletedAt(LocalDateTime.now());
        em.merge(post);  
    }
}
