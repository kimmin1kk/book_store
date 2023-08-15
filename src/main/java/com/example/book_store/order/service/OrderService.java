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

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductCartRepository productCartRepository;

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
    public void addProductToCart(long seq, String username, int count) {
        var user = userRepository.findByUsername(username);

        checkOrderCartByUsernameifEmptyThenCreate(username);

        var orderCart = cartRepository.findByUserUsername(username);
        Optional<Product> product = productRepository.findById(seq);

        if (product.isPresent()) {
            ProductCart existingProductCart = productCartRepository.findByOrderCartAndProduct(orderCart, product.get());

            if (existingProductCart == null) {
                ProductCart newProductCart = new ProductCart(orderCart, product.get(), count);
                productCartRepository.save(newProductCart);
            } else {
                int updatedCount = existingProductCart.getCount() + count;
                existingProductCart.setCount(updatedCount);
                productCartRepository.save(existingProductCart);
            }
        }
    }
}
