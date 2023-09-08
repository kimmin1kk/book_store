package com.example.book_store.order.controller;

import com.example.book_store.order.domain.ProductCart;
import com.example.book_store.order.repository.ProductCartRepository;
import com.example.book_store.order.service.OrderCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class OrderCartController {

    private final OrderCartService orderCartService;
    private final ProductCartRepository productCartRepository;

    @GetMapping("/shopping-cart")
    public String shoppingCart(Model model, Principal principal) {
        orderCartService.getOrderCart(principal.getName());
        model.addAttribute("orderCart", orderCartService.findOrderCart(principal.getName()));
        model.addAttribute("totalPrice", orderCartService.findTotalPrice(principal.getName()));
        return "shop/shoppingCart";

    }

    /**
     * 장바구니에 담기
     *
     * @param seq   상품 PK
     * @param count 상품 수
     * @return
     */
    @PostMapping("/add-to-cart/{seq}")
    public String addToCart(Model model, Principal principal, @PathVariable("seq") Long seq, @RequestParam(value = "count", required = false) Integer count) {
        if (count == null) {
            count = 1; // count가 null일 경우 기본값으로 1 할당
        }

        orderCartService.addProductToCart(seq, principal.getName(), count);
        return "redirect:/";
    }

    @GetMapping("/delete-product-from-cart/{seq}")
    public String deleteProductFromCart(@PathVariable("seq") Long seq, Principal principal) {
        productCartRepository.deleteById(seq);
        return "redirect:/shopping-cart";
    }

    @PostMapping("edit-product-count-from-cart/{seq}")
    public String editProductCountFromCart(@RequestParam("count") Integer count, @PathVariable("seq") Long seq, Principal principal) {
        Optional<ProductCart> productCartOptional = productCartRepository.findById(seq);
        if (productCartOptional.isPresent()) {
            ProductCart productCart = productCartOptional.get();
            productCart.setCount(count);
            productCartRepository.save(productCart);
        }
        return "redirect:/shopping-cart";
    }
}
