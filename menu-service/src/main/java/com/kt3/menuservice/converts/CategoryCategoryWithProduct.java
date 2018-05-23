package com.kt3.menuservice.converts;

import com.kt3.menuservice.commands.CategoryWithProduct;
import com.kt3.menuservice.model.Category;

import javax.persistence.Convert;
import org.springframework.core.convert.*;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryCategoryWithProduct implements Converter<Category, CategoryWithProduct> {
    @Override
    public CategoryWithProduct convert(Category source) {
        if(source == null) {
            return null;
        }
        final CategoryWithProduct categoryWithProduct = new CategoryWithProduct();
        categoryWithProduct.setId(source.getId());
        categoryWithProduct.setCode(source.getCode());
        categoryWithProduct.setDescription(source.getDescription());
        categoryWithProduct.setImage(source.getImage());
        categoryWithProduct.setName(source.getName());
        categoryWithProduct.setProducts(source.getProducts());
        categoryWithProduct.setStatus(source.isStatus());
        categoryWithProduct.setUpdateDate(source.getUpdateDate());
        for (Category childCat: source.getChilds()
             ) {
            categoryWithProduct.getProducts().addAll(childCat.getProducts());
        }
        return categoryWithProduct;
    }
}
