package com.example.book_store.home.controller;

import com.example.book_store.product.service.ProductService;
import com.example.book_store.user.service.UserDetailsServiceImpl;
import com.example.book_store.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.book_store.product.domain.Product;

import java.security.Principal;
import java.util.List;


@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        List<Product> displayProducts = productService.productList()  ;
        log.info("productList is : " + productService.productList());
        model.addAttribute("products", displayProducts);

        return "index";
    }

    @GetMapping("/my-page")
    public String myPage(Model model, Principal principal) {
        log.info("HomeController -> myPage : OK");
        model.addAttribute("user", userService.findUserInformationByUsername(principal.getName()));
        return "account/myPage";
    }

}
