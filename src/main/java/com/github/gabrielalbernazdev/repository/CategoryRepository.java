package com.github.gabrielalbernazdev.repository;

import java.util.List;

import com.github.gabrielalbernazdev.domain.model.Category;

public interface CategoryRepository {
    List<Category> findAll();
}
