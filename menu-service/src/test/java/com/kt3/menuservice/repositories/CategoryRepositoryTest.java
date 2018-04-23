package com.kt3.menuservice.repositories;

import com.kt3.menuservice.model.Category;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class CategoryRepositoryTest {
    @Mock
    CategoryRepository categoryRepository;

    Set<Category> categories = new HashSet<>();
    Set<Category> catCodes = new HashSet<>();


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void findCategoriesByCodeContains() {

        Category cat1 = new Category();
        Category cat2 = new Category();
        Category cat3 = new Category();
        Category cat4 = new Category();
        Category cat5 = new Category();
        cat1.setName("Cat1");
        cat2.setName("Cat2");
        cat3.setName("Cat3");
        cat4.setName("Cat4");
        cat5.setName("Cat5");

        cat1.setCode("MilkTea");
        cat2.setCode("Cat2");
        cat3.setCode("Cat3");
        cat4.setCode("Cat4");
        cat5.setCode("Cat5");


        categories.add(cat1);
        categories.add(cat2);
        categories.add(cat3);
        categories.add(cat4);
        categories.add(cat5);

        catCodes.add(cat1);
       // Mockito.when(categoryRepository.findCategoriesByCodeContains("MilkTea")).thenReturn(catCodes);

        assertEquals(true, categoryRepository.findCategoriesByCodeContains("MilkTea").contains(cat1));
    }

    @Test
    public void findCategoriesByNameContains() {
    }
}