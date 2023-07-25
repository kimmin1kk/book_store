package com.example.book_store.home.controller;

import com.example.book_store.home.service.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

}
