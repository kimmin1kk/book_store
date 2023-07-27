package com.example.book_store.home.controller;

import com.example.book_store.user.common.RegistrationForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/register")
    public String register(WebRequest request, Model model) {
        RegistrationForm userRegisterDto = new RegistrationForm();
        model.addAttribute("user", userRegisterDto);
        return "register";
    }

    @RequestMapping("/myPage")
    public String myPage() {
        return "myPage";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "login";
    }
}
