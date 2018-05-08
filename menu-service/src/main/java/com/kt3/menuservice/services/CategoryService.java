package com.kt3.menuservice.services;

import com.kt3.menuservice.commands.CategoryWithProduct;
import com.kt3.menuservice.model.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    List<Category> getCategories();
    List<CategoryWithProduct> getCategoriesWithProduct();
    Category findById(Long id);
    List<Category> searchCategoryByName(String name);
    List<Category> searchCategoryByCode(String code);
    Category addCategory(Category category);
    void deleteCategoryById(Long Id);
    void saveCategory(Category category);


}
