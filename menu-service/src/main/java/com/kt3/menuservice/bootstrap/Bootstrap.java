package com.kt3.menuservice.bootstrap;

import com.kt3.menuservice.model.Category;
import com.kt3.menuservice.model.Product;
import com.kt3.menuservice.repositories.CategoryRepository;
import com.kt3.menuservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
//@Component
public class Bootstrap implements CommandLineRunner {


    private CategoryRepository categoryRespository;
    private ProductRepository productRepository;


    public Bootstrap(CategoryRepository categoryRespository, ProductRepository productRepository) {
        this.categoryRespository = categoryRespository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        Category traDitionalMilkTea = categoryRespository.getOne(1L);
//
//        Product p1 = new Product();
//        p1.setName("Trà sữa Socola");
//        p1.setCode("Tra-sua-Socola");
//        p1.setDescription("Hương thơm mà socola mang lại là một sự quyến rũ kỳ diệu khi được đưa vào chế biến. Thêm vào đó, vị sữa ngọt ngào đã giúp trà sữa socola của bạn có một hương vị tuyệt vời mà ít loại thức uống nào có thể sánh bằng.");
//        p1.setThumbnail("/product/images/10-socola.jpg");
//        p1.setCategory(traDitionalMilkTea);
//
//        productRepository.save(p1);
//
//        Product p2 = new Product();
//        p1.setName("Trà sữa Kiwi");
//        p1.setCode("Tra-sua-Kiwi");
//        p1.setDescription("Với công dụng đẹp da, trẻ hoá da và giữ dáng, giúp kéo dài tuổi thanh xuân thì trà sữa kiwi đang được rất nhiều bạn trẻ yêu thích.");
//        p1.setThumbnail("/product/images/25-TS-kiwi.jpg");
//        p1.setCategory(traDitionalMilkTea);
//
//        productRepository.save(p1);
//        productRepository.save(p2);


//        Category creamMilkTea = new Category();
//        creamMilkTea.setName("Trà Sữa Kem");
//        creamMilkTea.setCode("TraSuaKem");
//        creamMilkTea.setDescription("Trà sữa có lớp kem béo ngậy");
//        creamMilkTea.setImage("/category/images/tra-sua-kem.png");
//
//        Category bubleTea = new Category();
//        bubleTea.setName("Trà Sữa Trân Châu");
//        bubleTea.setCode("TraSuaTranChau");
//        bubleTea.setDescription("Trà sữa bổ sung trân châu cực kỳ ngon miệng");
//        bubleTea.setImage("/category/images/tra-sua-tran-chau.png");
//
//
//
//        List<Product> products = new ArrayList<>();
//        Product p1 = new Product();
//        p1.setName("Trà Sữa Vị Khoai Môn");
//        p1.setCode("TraSuaTruyenThong-KhoaiMon");
//        p1.setCategory(traDitionalMilkTea);
//        products.add(p1);
//
//        categoryRespository.save(traDitionalMilkTea);
//        categoryRespository.save(creamMilkTea);
//        categoryRespository.save(bubleTea);
//
//        productRepository.save(p1);
//        System.out.println("Data Loaded = " + categoryRespository.count() );
//        List<Product> productList = productRepository.findProductsByCodeContainsOrNameContains("tra", "tra");
//        for (Product p: productList
//             ) {
//            System.out.println(p.getName());
//        }



    }
}
