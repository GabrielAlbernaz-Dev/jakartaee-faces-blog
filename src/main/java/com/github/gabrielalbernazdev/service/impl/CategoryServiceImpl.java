package com.github.gabrielalbernazdev.service.impl;

import java.util.List;

import com.github.gabrielalbernazdev.domain.model.Category;
import com.github.gabrielalbernazdev.repository.CategoryRepository;
import com.github.gabrielalbernazdev.service.CategoryService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CategoryServiceImpl implements CategoryService {
    @Inject
    private CategoryRepository repository;

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }
}
