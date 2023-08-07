package com.example.book_store.user.controller;

import com.example.book_store.user.common.RegistrationForm;
import com.example.book_store.user.repository.UserRepository;
import com.example.book_store.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
@Slf4j
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/register")
    public String register(WebRequest request, Model model) {
        log.info("HomeController -> register : OK");
        RegistrationForm userRegisterDto = new RegistrationForm();
        model.addAttribute("user", userRegisterDto);
        return "register";
    }

    @RequestMapping("/login")
    public String login() {
        return "account/login";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "index";
    }

}
