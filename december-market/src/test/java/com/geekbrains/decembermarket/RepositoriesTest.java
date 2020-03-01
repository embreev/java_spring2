package com.geekbrains.decembermarket;


import com.geekbrains.decembermarket.entites.Product;
import com.geekbrains.decembermarket.repositories.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoriesTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void addProductToRepositoryTest() {
        Product product = new Product();
        Product out = entityManager.persist(product);
        entityManager.flush();

        List<Product> productsList = (List<Product>) productRepository.findAll();
        System.out.println(productsList);

        Assert.assertEquals(4, productsList.size());
    }
}
