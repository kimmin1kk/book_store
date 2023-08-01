package com.example.book_store.home.controller;

import com.example.book_store.home.service.HomeService;
import com.example.book_store.user.common.RegistrationForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import com.example.book_store.product.domain.Product;

import java.security.Principal;
import java.util.List;


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
        List<Product> displayProducts = homeService.productList();
        log.info("productList is : " + homeService.productList());
        model.addAttribute("products", displayProducts);

        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "account/login";
    }

    @RequestMapping("/register")
    public String register(WebRequest request, Model model) {
        log.info("HomeController -> register : OK");
        RegistrationForm userRegisterDto = new RegistrationForm();
        model.addAttribute("user", userRegisterDto);
        return "register";
    }

    //    @RequestMapping("/myPage")
    @GetMapping("/myPage")
    public String myPage(Model model, Authentication auth) {
        log.info("HomeController -> myPage : OK");
        String username = auth.getName();
        model.addAttribute("username", username);
        return "account/myPage";
    }

    @GetMapping("/singleProduct")
    public String singleProduct(Model model) {
        log.info("HomeController -> singleProduct : OK");
        return "shop/singleProduct";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "index";
    }
}
