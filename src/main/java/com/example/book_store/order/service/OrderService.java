package com.example.book_store.order.service;

import com.example.book_store.order.domain.OrderCart;
import com.example.book_store.order.domain.ProductCart;
import com.example.book_store.order.repository.CartRepository;
import com.example.book_store.order.repository.ProductCartRepository;
import com.example.book_store.product.domain.Product;
import com.example.book_store.product.repository.ProductRepository;
import com.example.book_store.user.domain.User;
import com.example.book_store.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductCartRepository productCartRepository;

//    public boolean checkOrderCartByUsername(String username) {
//        User user = userRepository.findByUsername(username);
//        return user.getOrderCartList() != null;
//    } //true 일 때 책 추가, false 일 때 장바구니 생성 후 책 추가

    public void checkOrderCartByUsernameifEmptyThenCreate(String username) {
        User user = userRepository.findByUsername(username);
        if (user.getOrderCartList().isEmpty()) {
            createOrderCartByUsername(username);
        }

    }

    public void createOrderCartByUsername(String username) {
        User user = userRepository.findByUsername(username);
        OrderCart orderCart = new OrderCart(user);
        cartRepository.save(orderCart);
        log.info("OrderSerivce -> create Cart : OK  Cart = " + orderCart );
    }

    public void addProductToCart(@PathVariable long seq, String username) {
        var user = userRepository.findByUsername(username);

//        if (checkOrderCartByUsername(username)) {
//            createOrderCartByUsername(username);
//            log.info("OrderService -> addProductToCart -> OrderCart is null -> create OrderCartByUsername : OK");
//        }
        checkOrderCartByUsernameifEmptyThenCreate(username);

        var orderCart = cartRepository.findByUserUsername(username);
        Optional<Product> product = productRepository.findById(seq);

        if (product.isPresent()) {
            ProductCart productCart = new ProductCart(orderCart, product.get());
            productCartRepository.save(productCart);
        }
    }

}
