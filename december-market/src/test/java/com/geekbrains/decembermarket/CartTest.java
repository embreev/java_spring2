package com.geekbrains.decembermarket;

import com.geekbrains.decembermarket.beans.Cart;
import com.geekbrains.decembermarket.entites.Product;
import com.geekbrains.decembermarket.repositories.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTest {
    @Autowired
    private Cart cart;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void cartFillTest() {
        for (Product p: productRepository.findAll()) {
            cart.add(p);
        }
        Assert.assertEquals(productRepository.findAll().size(), cart.getItems().size());
    }
}