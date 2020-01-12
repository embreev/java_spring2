package com.geekbrains.decembermarket.controllers;

import com.geekbrains.decembermarket.entites.Order;
import com.geekbrains.decembermarket.entites.User;
import com.geekbrains.decembermarket.services.OrderService;
import com.geekbrains.decembermarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private OrderService orderService;

    @Autowired
    public UserController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login_page";
    }

    @GetMapping("/profile")
    public String profilePage(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/";
        }
        User user = userService.findByPhone(principal.getName());
        List<Order> orders = orderService.getOrders(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("orders", orders);
        return "profile";
    }

    @GetMapping("/register")
    public String redirectToFormRegistration() {
        return "sign_up_page";
    }

    @PostMapping("/signUp")
    public String addProductToCart(@ModelAttribute(name="user") User user){
        if (!userService.isUserExist(user.getPhone())) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userService.createUser(user);
//            userService.setRoleRepository();
            return "redirect:/user/profile";
        }
        return "redirect:/";
    }
}