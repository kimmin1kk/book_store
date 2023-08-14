package com.example.book_store.home.controller;

import com.example.book_store.product.service.ProductService;
import com.example.book_store.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.book_store.product.domain.Product;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;


@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        log.info("productList is : " + productService.productList());
        List<Product> displayProducts = productService.productList();
        model.addAttribute("products", displayProducts);

        return "index";
    }

    @GetMapping("/my-page")
    public String myPage(Model model, Principal principal) {
        log.info("HomeController -> myPage : OK");
        model.addAttribute("user", userService.findUserInformationByUsername(principal.getName()));

        model.addAttribute("cardList", userService.findUserCardListByUsername(principal.getName()));
        model.addAttribute("addressList", userService.findUserAddressListByUsername(principal.getName()));

        return "account/myPage";
    }

    @GetMapping("/delete-card/{seq}")
    public String deleteCardBySeq(@PathVariable long seq) {
        userService.deleteCardBySeq(seq);
        return "redirect:/my-page";
    }

    @GetMapping("/delete-address/{seq}")
    public String deleteAddressBySeq(@PathVariable long seq) {
        userService.deleteAddressBySeq(seq);
        return "redirect:/my-page";
    }
}
