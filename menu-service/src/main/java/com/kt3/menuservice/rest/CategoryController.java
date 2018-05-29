package com.kt3.menuservice.rest;

import com.kt3.menuservice.commands.CategoryWithProduct;
import com.kt3.menuservice.model.Category;
import com.kt3.menuservice.services.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {

    Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public List<Category> getAllCategory(){
        return categoryService.getCategories();
    }

    @GetMapping("/getall")
    public List<CategoryWithProduct> getAllCategoryWithProducts(){
        logger.info("GET:","Get all category WithProducts");
        return categoryService.getCategoriesWithProduct();
    }

    /*
    // Hàm này dùng để trang web truy vấn lấy dữ liệu
    // Mỗi Category hiển thị nhiều nhất 6 sản phẩm
     */
    @GetMapping("/getallHomePage")
    public List<CategoryWithProduct> getAllCategoryHomePage(){
        logger.info("GET:","Get all category HomePage");
        return categoryService.getCategoriesWithProduct();
    }

    @GetMapping("/{id}")
    public Category getCatgoryById(@PathVariable String id)
    {
        return categoryService.findById(Long.valueOf(id));
    }

}
