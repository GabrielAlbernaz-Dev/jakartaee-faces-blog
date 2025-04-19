package com.github.gabrielalbernazdev.repository.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.github.gabrielalbernazdev.domain.model.User;
import com.github.gabrielalbernazdev.domain.vo.Email;
import com.github.gabrielalbernazdev.repository.UserRepository;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Stateless
public class UserRepositoryImpl implements  UserRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findAll() {
        String jpql = "SELECT u FROM User u ORDER BY u.id";
        return em.createQuery(jpql, User.class)
                .getResultList();
    }

    @Override
    public List<User> findAllActive() {
        String jpql = "SELECT u FROM User u WHERE p.active = true ORDER BY u.id";
        return em.createQuery(jpql, User.class)
                .getResultList();
    }

    @Override
    public Optional<User> findById(UUID id) {
        final User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            final String jpql = "SELECT u FROM User u WHERE u.username = :username";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("username", username);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        try {
            final String jpql = "SELECT u FROM User u WHERE u.email = :email";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("email", email.getValue());
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
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
        User managedUser = em.find(User.class, user.getId());
        if (managedUser != null) {
            final String jpql = "UPDATE User u SET u.active = :active WHERE u.id = :id";
            Query query = em.createQuery(jpql);
    
            query.setParameter("active", false);
            query.setParameter("id", managedUser.getId());
    
            query.executeUpdate();
        }
    }
}
