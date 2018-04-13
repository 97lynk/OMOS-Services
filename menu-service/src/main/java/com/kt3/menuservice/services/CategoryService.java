package com.kt3.menuservice.services;

import com.kt3.menuservice.model.Category;

import java.util.Set;

public interface CategoryService {

    Set<Category> getCategories();
    Set<Category> searchCategoryByName(String name);
    Set<Category> searchCategoryByCode(String code);

}
