package com.example.book_store.product.controller;

import com.example.book_store.product.domain.Product;
import com.example.book_store.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
@Slf4j
public class ProductController {

    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/single-product/{seq}")
    public String singleProduct(Model model, @PathVariable("seq") Long seq, Authentication auth) {
        log.info("HomeController -> singleProduct : OK");
        Optional<Product> productOptional = productService.findProductBySeq(seq);
        if(auth != null){ //로그인 했을 때 Welcome, {닉네임} 을 띄우기 위해서
            String username = auth.getName();
            model.addAttribute("username", username);
        }



        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            model.addAttribute("product", product);
        }
        return "shop/singleProduct";
    }


}
