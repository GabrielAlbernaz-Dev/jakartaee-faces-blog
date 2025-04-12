package com.github.gabrielalbernazdev.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.github.gabrielalbernazdev.domain.model.User;
import com.github.gabrielalbernazdev.domain.vo.Email;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class UserRepository {
    @PersistenceContext
    private EntityManager em;

    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u ORDER BY u.id", User.class)
                .getResultList();
    }

    public Optional<User> findById(UUID id) {
        final User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

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

    public void save(User user) {
        if(user.getId() != null) {
            em.persist(user);
            return;
        }

        em.merge(user);
    }

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
