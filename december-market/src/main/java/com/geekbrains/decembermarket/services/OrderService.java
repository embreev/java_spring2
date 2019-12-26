package com.geekbrains.decembermarket.services;

import com.geekbrains.decembermarket.entites.Order;
import com.geekbrains.decembermarket.entites.Product;
import com.geekbrains.decembermarket.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getOrder(String userName) {
        return orderRepository.findAll().stream().filter(o -> o.getUser().getPhone().equals(userName)).findAny().get();
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }
}
