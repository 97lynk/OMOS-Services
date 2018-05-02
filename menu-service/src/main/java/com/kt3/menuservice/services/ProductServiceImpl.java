package com.kt3.menuservice.services;

import com.kt3.menuservice.model.Category;
import com.kt3.menuservice.model.Product;
import com.kt3.menuservice.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    CategoryService categoryService;

    Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);


    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> getProductsByName(String name) {
        log.info("FIND: ------ "+ name);
        return productRepository.findProductsByNameContaining(name);
    }

    @Override
    public List<Product> getProductsByCode(String code) {
        return productRepository.findProductsByCodeContaining(code);
    }

    @Override
    public List<Product> getProductsByCategory(Long catId) {
        Category category = categoryService.findById(catId);
        List<Category> listChildsCat = category.getChilds();
        Set<Product> productSet = new HashSet<>();
        productRepository.findProductsByCategory(category).iterator().forEachRemaining(productSet::add);
        for (Category cat: listChildsCat) {
            productRepository.findProductsByCategory(cat).iterator().forEachRemaining(productSet::add);
        }
        List<Product> productList = new ArrayList<>();
        productList.addAll(productSet);
        return productList;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> searchProduct(String s) {
        return productRepository.findProductsByCodeContainsOrNameContains(s, s);
    }
}
