package com.example.book_store.order.controller;

import com.example.book_store.order.common.OrderPageForm;
import com.example.book_store.order.service.OrderCartService;
import com.example.book_store.order.service.OrderService;
import com.example.book_store.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final OrderCartService orderCartService;
    private final UserService userService;

    /**
     * 장바구니에서 결제하기를 클릭했을 때 호출되는 컨트롤러
     *
     * @param model
     * @param principal
     * @return
     */
    @GetMapping("/order-page")
    public String orderPage(Model model, Principal principal) {
        model.addAttribute("orderCart", orderCartService.findOrderCart(principal.getName()));
        model.addAttribute("addresses", userService.findUserAddressListByUsername(principal.getName()));
        model.addAttribute("cards", userService.findUserCardListByUsername(principal.getName()));
        return "shop/orderPage";
    }

    /**
     * 바로구매를 클릭했을 때 호출되는 컨트롤러
     *
     * @param model
     * @param principal
     * @return
     */
    @GetMapping("/add-to-cart-instant/{seq}")
    public String orderPageForInstant(Model model, Principal principal, @PathVariable("seq") Long seq, @RequestParam(value = "count", required = false) Integer count) {
        orderCartService.getOrderCartForInstant(principal.getName());
        if (count == null) {
            count = 1;
        }
        orderCartService.addProductToCartForInstant(seq, principal.getName(), count);

        model.addAttribute("orderCart", orderCartService.findOrderCartForInstant(principal.getName()));
        model.addAttribute("addresses", userService.findUserAddressListByUsername(principal.getName()));
        model.addAttribute("cards", userService.findUserCardListByUsername(principal.getName()));
        return "shop/orderPage";
    }

    @PostMapping("/order-page")
    public String orderPage(Model model, Principal principal, @ModelAttribute OrderPageForm orderPageForm) {
        log.info("OrderController -> orderPage : OK , orderPageForm is " + orderPageForm.toString());

        if (orderPageForm.isOrderInstant()) {
            orderService.confirmOrder(orderPageForm, orderCartService.findOrderCartForInstant(principal.getName()));
        } else {
            orderService.confirmOrder(orderPageForm, orderCartService.findOrderCart(principal.getName()));
        }

        return "redirect:/";
    }

    @GetMapping("/order-history-page")
    public String orderHistoryPage(Model model, Principal principal) {
//        orderCartService.findOrderCart(principal.getName());
        model.addAttribute("orderedCarts", orderService.getOrderedCarts(principal.getName()));
        return "account/orderHistoryList";
    }


}
