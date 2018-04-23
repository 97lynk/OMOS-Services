package com.kt3.menuservice.services;

import com.kt3.menuservice.model.Category;
import com.kt3.menuservice.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn(categories);
        assertEquals(3, categoryService.getCategories().size());
    }

    @Test
    public void searchCategoryByName() {
        Category cat1 = new Category();
        cat1.setName("Name2343");
        Category cat2 = new Category();
        cat1.setName("Name234");
        Category cat13= new Category();
        cat1.setName("Name");
        Category cat3 = new Category();
        cat1.setName("Name");
        List<Category> categories = new ArrayList<>();
        categories.add(cat1);
        categories.add(cat2);
        categories.add(cat3);
        categories.add(cat13);

        Mockito.when(categoryRepository.findCategoriesByNameContains("name")).thenReturn(categories);
        assertEquals(4, categoryService.searchCategoryByName("name").size());
        assertEquals(0, categoryService.searchCategoryByName("notname").size());
    }

    @Test
    public void searchCategoryByCode() {
        Category cat1 = new Category();
        cat1.setCode("Code12121");
        Category cat2 = new Category();
        cat1.setCode("Code12121");
        Category cat13= new Category();
        cat1.setCode("Code12121");
        Category cat3 = new Category();
        cat1.setCode("Code12121");
        List<Category> categories = new ArrayList<>();
        categories.add(cat1);
        categories.add(cat2);
        categories.add(cat3);
        categories.add(cat13);

        Mockito.when(categoryRepository.findCategoriesByCodeContains("code")).thenReturn(categories);
        assertEquals(4, categoryService.searchCategoryByCode("code").size());
        assertEquals(0, categoryService.searchCategoryByCode("notcode").size());
    }

    @Test
    public void findById() {
        Category category = new Category();
        category.setId(1L);
        category.setName("CategoryName");
        category.setCode("CategoryCode");
        Optional<Category> categoryOptional = Optional.of(category);

        when(categoryRepository.findById(1L)).thenReturn(categoryOptional);
        Category result = categoryService.findById(1L);
        assertEquals(new Long(1L), result.getId());
        assertEquals("CategoryName", result.getName());
        assertEquals("CategoryCode", result.getCode());
    }

    @Test
    public void addCategory() {
            Category category = new Category();
            category.setId(1L);
            category.setName("CategoryName");
            category.setCode("CategoryCode");

            when(categoryRepository.save(category)).thenReturn(category);
            Category result = categoryService.addCategory(category);

            assertEquals(new Long(1L), category.getId());
            assertEquals("CategoryName", result.getName());
            assertEquals("CategoryCode", result.getCode());

    }

    @Test
    public void deleteCategoryById() {


        Category category = new Category();
        category.setId(1L);
        category.setName("CategoryName");
        category.setCode("CategoryCode");

        //Hàm này trả về một Optional khi gọi lệnh findById
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        categoryService.deleteCategoryById(1L);

        //Verify số lần gọi hàm delete của categoryRepository
        verify(categoryRepository, times(1)).delete(category);

    }

    @Test
    public void saveCategory() {

        Category category = new Category();
        category.setId(2L);
        category.setName("CategoryName");
        category.setCode("CategoryCode");

        when(categoryRepository.save(category)).thenReturn(category);
        categoryService.saveCategory(category);

        verify(categoryRepository, times(1)).save(category);

    }
}