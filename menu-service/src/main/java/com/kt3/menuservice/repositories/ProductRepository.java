package com.kt3.menuservice.repositories;

import com.kt3.menuservice.model.Category;
import com.kt3.menuservice.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByNameContaining(String name);
    List<Product> findProductsByCodeContaining(String code);
    List<Product> findProductsByCategory(Category category);
    List<Product> findProductsByCategoryId(Long Id, Pageable pageable);
    List<Product> findProductsByCodeContainsOrNameContains(String s1, String s2);
}
