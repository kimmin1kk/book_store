package com.example.book_store.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CardController {

    @GetMapping("/add-card")
    public String addCard() {
        return "/account/addCardForm";
    }
}
