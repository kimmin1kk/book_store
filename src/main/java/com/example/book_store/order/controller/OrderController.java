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

    @GetMapping("/add-to-cart")
    public String addToCart(Model model, Principal principal) {
        //상품 DTO를 유저의 장바구니로 넘겨주면 됨
        return "redirect:/";
    }


    @GetMapping("/order-page")
    public String orderPage(Model model, Principal principal) {
        return "shop/orderPage";
    }
}
