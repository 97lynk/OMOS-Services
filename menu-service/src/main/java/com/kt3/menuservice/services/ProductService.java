package com.kt3.menuservice.services;

import com.kt3.menuservice.model.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    List<Product> getProductHomePage(int catId, Pageable pageable);
    Product findById(Long id);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByCode(String code);
    List<Product> searchProduct(String s);
    List<Product> getProductsByCategory(Long catId);

    Product saveProduct(Product product);
    void deleteById(Long id);
}
