package com.example.book_store.home.controller;

import com.example.book_store.user.common.RegistrationForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.security.Principal;

@Controller
@RequestMapping("/")
@Slf4j
public class HomeController {
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/register")
    public String register(WebRequest request, Model model) {
        log.info("HomeController -> register : OK");
        RegistrationForm userRegisterDto = new RegistrationForm();
        model.addAttribute("user", userRegisterDto);
        return "register";
    }

    @RequestMapping("/myPage")
    public String myPage(Model model, Principal principal) {
        log.info("HomeController -> myPage : OK");
        return "myPage";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "index";
    }
}
