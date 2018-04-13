package com.kt3.menuservice.repositories;

import com.kt3.menuservice.model.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CategoryRepository extends CrudRepository<Category, Long>{
    Set<Category> findCategoriesByCodeContains(String code);
    Set<Category> findCategoriesByNameContains(String name);
}
