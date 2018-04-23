package com.kt3.menuservice.rest;

import com.kt3.menuservice.model.Category;
import com.kt3.menuservice.services.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    Logger logger = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    CategoryService categoryService;
    @GetMapping("")
    public List<Category> getAllCategory(){
        logger.info("GET:","Get all category");
        return categoryService.getCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable String id)
    {
        return categoryService.findById(Long.valueOf(id));
    }

//    @PostMapping
//    public String post(@RequestBody String str){
//        logger.info("POST " + str);
//        return "OK";
//    }
}
