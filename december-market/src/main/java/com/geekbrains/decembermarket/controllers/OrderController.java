package com.geekbrains.decembermarket.controllers;

import com.geekbrains.decembermarket.beans.Cart;
import com.geekbrains.decembermarket.entites.Order;
import com.geekbrains.decembermarket.entites.User;
import com.geekbrains.decembermarket.services.OrderService;
import com.geekbrains.decembermarket.services.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private UserService userService;
    private OrderService orderService;
    private Cart cart;

    public OrderController(UserService userService, OrderService orderService, Cart cart) {
        this.userService = userService;
        this.orderService = orderService;
        this.cart = cart;
    }

    @GetMapping("/info")
    public String showOrderInfo(Model model, Principal principal) {
        User user = userService.findByPhone(principal.getName());
        model.addAttribute("cart", cart);
        model.addAttribute("def_phone", user.getPhone());
        return "order_info_before_confirmation";
    }

    @GetMapping("/fast/info")
    public String showFastOrderInfo(Model model) {
        model.addAttribute("cart", cart);
        return "fast_order_info_before_confirmation";
    }

    @PostMapping("/fast/create")
    public String createFastOrder(Model model, @RequestParam(name = "phone") String phone) {
        User fastUser;
        if (!userService.isUserExist(phone)) {
            fastUser = new User(phone);
            userService.createUser(fastUser);
        } else {
            fastUser = userService.findByPhone(phone);
        }
        Order fastOrder = new Order(fastUser, cart, "", phone);
        fastOrder = orderService.save(fastOrder);
        model.addAttribute("order_id_str", String.format("%05d", fastOrder.getId()));
        return "order_confirmation";
    }

    @PostMapping("/create")
    public String createOrder(Principal principal, Model model, @RequestParam(name = "address") String address, @RequestParam("phone_number") String phone) {
        User user = userService.findByPhone(principal.getName());
        Order order = new Order(user, cart, address, phone);
        order = orderService.save(order);
        model.addAttribute("order_id_str", String.format("%05d", order.getId()));
        return "order_confirmation";
    }
}
