package com.kt3.menuservice.services;

import com.kt3.menuservice.model.Category;
import com.kt3.menuservice.repositories.CategoryRepository;

import java.util.HashSet;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getCategories() {
        Set<Category> categories = new HashSet<>();
        categoryRepository.findAll().iterator().forEachRemaining(categories::add);
        return categories;
    }

    @Override
    public Set<Category> searchCategoryByName(String name) {
        return null;
    }

    @Override
    public Set<Category> searchCategoryByCode(String code) {
        return null;
    }
}
