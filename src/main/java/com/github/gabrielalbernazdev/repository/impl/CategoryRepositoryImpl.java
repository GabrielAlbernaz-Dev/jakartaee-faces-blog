package com.github.gabrielalbernazdev.repository.impl;

import java.util.List;

import com.github.gabrielalbernazdev.domain.model.Category;
import com.github.gabrielalbernazdev.repository.CategoryRepository;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
public class CategoryRepositoryImpl implements CategoryRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Category> findAll() {
        TypedQuery<Category> q = em.createNamedQuery("Category.findAll", Category.class);
        return q.getResultList();
    }
}
