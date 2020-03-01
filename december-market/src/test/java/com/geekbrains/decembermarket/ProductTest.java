package com.geekbrains.decembermarket;

import com.geekbrains.decembermarket.entites.Product;
import com.geekbrains.decembermarket.repositories.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void getAllProductsTest() {
        for (Product p : productRepository.findAll()) {
            System.out.println(p);
            assertThat(p).isNotNull();
        }
    }
}
