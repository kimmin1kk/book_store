package com.example.book_store.order.controller;

import com.example.book_store.order.repository.ProductCartRepository;
import com.example.book_store.order.service.OrderCartService;
import com.example.book_store.order.service.OrderService;
import com.example.book_store.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final OrderCartService orderCartService;
    private final UserService userService;
    private final ProductCartRepository productCartRepository;


    @GetMapping("/order-page")
    public String orderPage(Model model, Principal principal) {
        model.addAttribute("orderCart", orderCartService.findCartByUsername(principal.getName()));
        log.info("orderCart is " + orderCartService.findCartByUsername(principal.getName()));
        model.addAttribute("addresses", userService.findUserAddressListByUsername(principal.getName()));
        log.info("addresses is " + userService.findUserAddressListByUsername(principal.getName()));
        model.addAttribute("cards", userService.findUserCardListByUsername(principal.getName()));
        log.info("cards is " + userService.findUserCardListByUsername(principal.getName()));
        return "shop/orderPage";
    }
}
