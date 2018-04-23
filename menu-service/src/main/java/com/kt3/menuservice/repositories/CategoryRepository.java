package com.kt3.menuservice.repositories;

import com.kt3.menuservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findCategoriesByCodeContains(String code);
    List<Category> findCategoriesByNameContains(String name);
}