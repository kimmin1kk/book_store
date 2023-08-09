package com.example.book_store.user.controller;

import com.example.book_store.user.common.AddAddressForm;
import com.example.book_store.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class AddressController {

    private final UserService userService;

    @GetMapping("add-address")
    public String addAddress() {
        return "/account/addAddressForm";
    }

    @PostMapping("add-address")
    public String addAddress(@ModelAttribute AddAddressForm addressForm, Principal principal) {
        userService.addAddressToUser(addressForm, principal.getName());
        return "redirect:/my-page";
    }
}
