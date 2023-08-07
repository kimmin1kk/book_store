package com.example.book_store.home.controller;

import com.example.book_store.home.service.HomeService;
import com.example.book_store.user.common.RegistrationForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.book_store.product.domain.Product;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Controller
@Slf4j
public class HomeController {

    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            model.addAttribute("username", username);
        }
        List<Product> displayProducts = homeService.productList()  ;
        log.info("productList is : " + homeService.productList());
        model.addAttribute("products", displayProducts);

        return "index";
    }

    @GetMapping("/my-page")
    public String myPage(Model model, Authentication auth) {
        log.info("HomeController -> myPage : OK");
        String username = auth.getName();
        model.addAttribute("username", username);
        return "account/myPage";
    }


}
