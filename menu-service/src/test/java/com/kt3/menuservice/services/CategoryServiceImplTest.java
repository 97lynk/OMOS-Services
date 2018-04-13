package com.kt3.menuservice.services;

import com.kt3.menuservice.model.Category;
import com.kt3.menuservice.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    public void getCategories() throws Exception{
        //given
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        //when
        Set<Category> categorySet = categoryService.getCategories();

        //then
        assertEquals(3, categorySet.size());
    }

    @Test
    public void searchCategoryByName() {
    }

    @Test
    public void searchCategoryByCode() {
    }
}