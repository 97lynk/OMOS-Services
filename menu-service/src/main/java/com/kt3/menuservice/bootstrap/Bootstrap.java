package com.kt3.menuservice.bootstrap;

import com.kt3.menuservice.model.Category;
import com.kt3.menuservice.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRespository;

    public Bootstrap(CategoryRepository categoryRespository) {
        this.categoryRespository = categoryRespository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Cat1");

        Category dried = new Category();
        dried.setName("Cat2");

        Category fresh = new Category();
        fresh.setName("Cat3");

        Category exotic = new Category();
        exotic.setName("Cat4");

        Category nuts = new Category();
        nuts.setName("Cat5");

        categoryRespository.save(fruits);
        categoryRespository.save(dried);
        categoryRespository.save(fresh);
        categoryRespository.save(exotic);
        categoryRespository.save(nuts);


        System.out.println("Data Loaded = " + categoryRespository.count() );

    }
}
