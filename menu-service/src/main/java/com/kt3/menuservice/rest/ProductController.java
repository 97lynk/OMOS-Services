package com.kt3.menuservice.rest;


import com.kt3.menuservice.model.Product;
import com.kt3.menuservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/category/{id}")
    public List<Product> getProductById(@PathVariable String id){
        return productService.getProductsByCategory(Long.valueOf(id));
    }

    @GetMapping("/search")
    public List<Product> searchProduct(@RequestParam("s") String s){
        if (s.equals("")) {
            return new ArrayList<Product>();
        }
        return productService.searchProduct(s);
    }

}