package com.kt3.menuservice.repositories;

import com.kt3.menuservice.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long>{
}
