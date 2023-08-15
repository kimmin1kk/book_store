package com.example.book_store.order.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class OrderController {



    @GetMapping("/order-page")
    public String orderPage(Model model, Principal principal) {
        return "shop/orderPage";
    }
}
