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

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class OrderCartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductCartRepository productCartRepository;

    public OrderCart findCartByUsername(String username) {
        return cartRepository.findByUserUsername(username);
    }

    public void getOrderCart(String username) {
        User user = userRepository.findByUsername(username);
        if (user.getOrderCartList().isEmpty()) {
            createOrderCartByUsername(username);
        }
    }

    public int findTotalPrice(String username) {
        OrderCart orderCart = cartRepository.findByUserUsername(username);
        List<ProductCart> productCartList = orderCart.getProductCartList();
        return productCartList.stream()
                .mapToInt(i -> i.getProduct().getPrice() * i.getCount())
                .sum();
    }

    public void createOrderCartByUsername(String username) {
        User user = userRepository.findByUsername(username);
        OrderCart orderCart = new OrderCart(user);
        cartRepository.save(orderCart);
        log.info("OrderSerivce -> create Cart : OK  Cart = " + orderCart);
    }

    public void addProductToCart(long seq, String username, int count) {
        getOrderCart(username);

        var orderCart = cartRepository.findByUserUsername(username);
        Optional<Product> product = productRepository.findById(seq);

        if (product.isPresent()) { //Optional<Product>기 때문에 isPresent 메서드를 통해 검증하는 과정이 있어야함
            ProductCart existingProductCart = productCartRepository.findByOrderCartAndProduct(orderCart, product.get());

            if (existingProductCart == null) { //이미 장바구니에 있는 상품일 경우, 카운트만 올라가게 하는 로직
                ProductCart newProductCart = new ProductCart(orderCart, product.get(), count);
                productCartRepository.save(newProductCart);
            } else {
                int updatedCount = existingProductCart.getCount() + count;
                existingProductCart.setCount(updatedCount);
                productCartRepository.save(existingProductCart);
            }
//            findTotalPrice(username);
        }
    }
}
