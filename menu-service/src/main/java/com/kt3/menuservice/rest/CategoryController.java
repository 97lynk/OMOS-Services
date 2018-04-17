package com.kt3.menuservice.rest;

import com.kt3.menuservice.model.Category;
import com.kt3.menuservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("")
    public List<Category> getAllCategory(){
        return categoryService.getCategories();
    }

    @GetMapping("/{id}")
    public Category getCatgoryById(@PathVariable String id)
    {
        return categoryService.findById(Long.valueOf(id));
    }

}
