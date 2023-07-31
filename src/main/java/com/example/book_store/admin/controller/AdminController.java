package com.example.book_store.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    public String showAdminPage() {
        return "admin/main";
    }
}
