package com.example.book_store.order.controller;

import com.example.book_store.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/shopping-cart")
    public String shoppingCart(Model model, Principal principal) {
        return "shop/shoppingCart";
    }

     @PostMapping("/add-to-cart/{seq}")
    public String addToCart(Model model, Principal principal, @PathVariable("seq") Long seq, @RequestParam("count") int count) {
        orderService.addProductToCart(seq,principal.getName(), count);
        return "redirect:/";
    }


    @GetMapping("/order-page")
    public String orderPage(Model model, Principal principal) {
        return "shop/orderPage";
    }
}
