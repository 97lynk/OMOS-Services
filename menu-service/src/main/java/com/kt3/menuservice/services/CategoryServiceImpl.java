package com.kt3.menuservice.services;

import com.kt3.menuservice.commands.CategoryWithProduct;
import com.kt3.menuservice.converts.CategoryCategoryWithProduct;
import com.kt3.menuservice.model.Category;
import com.kt3.menuservice.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;
    @Autowired
    CategoryCategoryWithProduct categoryCategoryWithProduct;
    @Autowired
    ProductService productService;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<CategoryWithProduct> getCategoriesWithProduct() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryWithProduct> categoryWithProducts = new ArrayList<>();
        for (Category cat: categories
             ) {
            CategoryWithProduct categoryWithProduct = categoryCategoryWithProduct.convert(cat);
            if(categoryWithProduct.getProducts().size() > 6)
            {
                categoryWithProduct.setProducts(categoryWithProduct.getProducts().subList(0, 6));
            }
            categoryWithProducts.add(categoryWithProduct);

        }
        return categoryWithProducts;
    }

    @Override
    public List<Category> searchCategoryByName(String name) {
        return categoryRepository.findCategoriesByNameContains(name);
    }

    @Override
    public List<Category> searchCategoryByCode(String code) {
        return categoryRepository.findCategoriesByCodeContains(code);
    }

    @Override
    public Category findById(Long id){
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        return  categoryOptional.get();
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        categoryRepository.delete(category.get());
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }
}
