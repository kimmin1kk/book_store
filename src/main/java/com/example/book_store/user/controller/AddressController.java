package com.example.book_store.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddressController {

    @GetMapping("add-address")
    public String addAddress() {
        return "/account/addAddressForm";
    }
}
