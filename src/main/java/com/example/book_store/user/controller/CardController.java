package com.example.book_store.user.controller;

import com.example.book_store.user.common.AddCardForm;
import com.example.book_store.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CardController {

    private final UserService userService;

    @GetMapping("/add-card")
    public String addCard() {
        return "/account/addCardForm";
    }

    @PostMapping("/add-card")
    public String addCard(@ModelAttribute AddCardForm cardForm, Principal principal) {
        userService.addCardToUser(cardForm, principal.getName());
        return "redirect:/my-page";
    }
}
