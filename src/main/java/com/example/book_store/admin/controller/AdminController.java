package com.example.book_store.admin.controller;

import com.example.book_store.admin.service.AdminService;
import com.example.book_store.home.service.HomeService;
import com.example.book_store.product.domain.Product;
import com.example.book_store.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
public class AdminController {
    private final AdminService adminService;
    private final HomeService homeService;

    @Autowired
    public AdminController(AdminService adminService, HomeService homeService) {
        this.adminService = adminService;
        this.homeService = homeService;
    }

    @GetMapping("/user-list")
    public String userList(Model model, Principal principal) {
        log.info("AdminController -> userList : OK");
        List<User> users = adminService.userList();
        log.info("userList is " + adminService.userList());
        model.addAttribute("users", users);

        return "admin/userList";
    }

    @GetMapping("/product-list")
    public String productList(Model model) {
        log.info("AdminController -> productList : OK");
        List<Product> products = homeService.productList();
        log.info("productList is " + homeService.productList());
        model.addAttribute("products", products);
        return "admin/productList";
    }

}
