package com.example.book_store.product.controller;

import com.example.book_store.product.common.Category;
import com.example.book_store.product.domain.Product;
import com.example.book_store.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.security.Principal;
import java.util.List;
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
    public String singleProduct(Model model, Principal principal, @PathVariable("seq") Long seq) {
        log.info("HomeController -> singleProduct : OK");
        Optional<Product> productOptional = productService.findProductBySeq(seq);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            model.addAttribute("product", product);
        }
        return "shop/singleProduct";
    }

    @GetMapping("/search-product")
    public String searchProduct(Model model, Principal principal, String keyword, Category category) {
        List<Product> products = productService.searchProductList(keyword, category);
        model.addAttribute("products", products);
        log.info("ProductController -> searchProduct : OK, products :" + products);
        return "index";
    }
}
