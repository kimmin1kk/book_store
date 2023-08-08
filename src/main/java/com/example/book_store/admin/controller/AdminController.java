package com.example.book_store.admin.controller;

import com.example.book_store.admin.service.AdminService;
import com.example.book_store.product.domain.Product;
import com.example.book_store.product.service.ProductService;
import com.example.book_store.user.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class AdminController {
    private final AdminService adminService;
    private final ProductService productService;

    @Autowired
    public AdminController(AdminService adminService, ProductService productService) {
        this.adminService = adminService;
        this.productService = productService;
    }

    @GetMapping("/product-list")
    public String productList(Model model, Principal principal) {
        log.info("AdminController -> productList : OK");
        List<Product> products = productService.productList();
        log.info("productList is " + productService.productList());
        model.addAttribute("products", products);
        return "admin/productList";
    }
    @GetMapping("/product-edit-form/{seq}")
    public String updateProduct(Model model, @PathVariable("seq") Long seq) {
        log.info("AdminController -> updateProduct(GET) : OK");
        Optional<Product> productOptional = productService.findProductBySeq(seq);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            model.addAttribute("product", product);
        }
        return "/admin/productEditForm";
    }

    @PostMapping("/product-edit-form/{seq}")
    public String updateProduct(Product product, @PathVariable("seq") Long seq) {
        log.info("AdminController -> updateProduct(POST) : OK");
        adminService.updateProduct(seq, product);
        return "redirect:/product-list";
    }

    @GetMapping("/delete-product/{seq}")
    public String deleteProduct(@PathVariable("seq") Long seq) {
        log.info("AdminController -> deleteProduct : OK");
        adminService.deleteProductBySeq(seq);
        return "redirect:/product-list";
    }

    //---------
    @GetMapping("/user-list")
    public String userList(Model model, Principal principal) {
        log.info("AdminController -> userList : OK");
        List<User> users = adminService.userList();
        log.info("userList is " + adminService.userList());
        return "admin/userList";
    }
    @GetMapping("/user-edit-form/{seq}")
    public String updateUser(Model model, @PathVariable("seq") Long seq) {
        log.info("AdminController -> updateUser(GET) : OK");
        Optional<User> userOptional = adminService.findUserBySeq(seq);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            model.addAttribute("user", user);
        }
        return "/admin/userEditForm";
    }

    @PostMapping("/user-edit-form/{seq}")
    public String updateUser(User user, @PathVariable("seq") Long seq) {
        log.info("AdminController -> updateUser(POST) : OK");
        adminService.updateUser(seq, user);
        return "redirect:/user-list";
    }

    @GetMapping("/delete-user/{seq}")
    public String deleteUser(@PathVariable("seq") Long seq) {
        log.info("AdminController -> deleteUser : OK");
        adminService.deleteUserBySeq(seq);
        return "redirect:/user-list";
    }


}
