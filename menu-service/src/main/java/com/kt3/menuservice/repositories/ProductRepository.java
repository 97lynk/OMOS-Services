package com.kt3.menuservice.repositories;

import com.kt3.menuservice.model.Category;
import com.kt3.menuservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByNameContains(String name);
    List<Product> findProductsByCodeContains(String code);
    List<Product> findProductsByCategory(Category category);
    List<Product> findProductsByCodeContainsOrNameContains(String s1, String s2);
}
