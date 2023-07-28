package com.example.book_store.user.controller;

import com.example.book_store.user.common.RegistrationForm;
import com.example.book_store.user.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
@Slf4j
public class RegistrationController {
    private final UserServiceImpl userService;
    @Autowired
    public RegistrationController(UserServiceImpl userService) {
        this.userService = userService;
    }
    @GetMapping
    public String registerForm() {
        log.info("RegistrationController -> registerForm 연결 성공");
        return "account/register";
    }
    @PostMapping
    public String processRegistration(RegistrationForm registrationForm) {
        userService.processRegistration(registrationForm);
        log.info("registrationController/processRegistration : OK");
        return "redirect:/login";
    }
}
