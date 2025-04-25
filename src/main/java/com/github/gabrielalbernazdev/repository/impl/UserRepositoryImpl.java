package com.github.gabrielalbernazdev.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.github.gabrielalbernazdev.domain.model.User;
import com.github.gabrielalbernazdev.domain.vo.Email;
import com.github.gabrielalbernazdev.repository.UserRepository;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class UserRepositoryImpl implements  UserRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findAll() {
        return em
          .createNamedQuery("User.findAll", User.class)
          .getResultList();
    }

    @Override
    public List<User> findAllActive() {
        return em
          .createNamedQuery("User.findAllActive", User.class)
          .getResultList();
    }

    @Override
    public Optional<User> findById(UUID id) {
        User u = em.find(User.class, id);
        return Optional.ofNullable(u);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        TypedQuery<User> q = em.createNamedQuery("User.findByUsername", User.class);
        q.setParameter("username", username);
        return q.getResultStream().findFirst();
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        TypedQuery<User> q = em.createNamedQuery("User.findByEmail", User.class);
        q.setParameter("email", email);
        return q.getResultStream().findFirst();
    }

    @Override
    public UUID save(User user) {
        if (user.getId() == null) {
            em.persist(user);      
            em.flush();            
            return user.getId();
        }

        User managed = em.merge(user); 
        em.flush();
        return managed.getId();
    }

    @Override
    public void delete(User user) {
        user.setActive(false);
    }
}
