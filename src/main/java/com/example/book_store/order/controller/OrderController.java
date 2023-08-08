package com.example.book_store.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class OrderController {

    @GetMapping("/shopping-cart")
    public String shoppingCart(Model model, Principal principal) {
        return "shop/shoppingCart";
    }
}
