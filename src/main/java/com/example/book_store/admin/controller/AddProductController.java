package com.example.book_store.admin.controller;

import com.example.book_store.admin.common.ProductAddForm;
import com.example.book_store.admin.service.AdminServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/addProduct")
@Slf4j
public class AddProductController {

    private final AdminServiceImpl adminService;

    @Autowired
    public AddProductController(AdminServiceImpl adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String ProductAddForm() {
        log.info("AddProductController -> ProductAddForm : OK");
        return "admin/productForm";
    }

    @PostMapping
    public String processAddProduct(ProductAddForm productAddForm) {
        log.info("AddProductController -> processAddProduct : OK");
        adminService.processingAddProduct(productAddForm);
        return "redirect:/myPage";
    }

}
