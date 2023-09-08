package com.example.book_store.order.service;

import com.example.book_store.order.domain.OrderCart;
import com.example.book_store.order.domain.ProductCart;
import com.example.book_store.order.repository.CartRepository;
import com.example.book_store.order.repository.ProductCartRepository;
import com.example.book_store.product.domain.Product;
import com.example.book_store.product.repository.ProductRepository;
import com.example.book_store.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    @Transactional
    public void deleteOrderCart(Long seq) {
        OrderCart cart = cartRepository.findBySeq(seq);
        if (cart.isInstant()) {
            cartRepository.deleteById(seq);
        }

    }

    /**
     * 장바구니 구매용
     * OrderCart 리스트를 가져와서 isOrdered == false && isInstant == false인 OrderCart의 Seq로 찾는 로직
     *
     * @param username
     * @return 장바구니
     */
    public OrderCart findOrderCart(String username) {
        Long cartSeq = null;
        for (OrderCart findCart : cartRepository.findOrderCartsByUserUsername(username)) {
            if (!findCart.isOrdered() && !findCart.isInstant()) {
                cartSeq = findCart.getSeq();
            }
        }
        return cartRepository.findBySeq(cartSeq);
    }


    /**
     * 바로 구매용
     * OrderCart 리스트를 가져와서 isOrdered == false && isInstant == true인 OrderCart의 Seq로 찾는 로직
     *
     * @param username
     * @return 장바구니
     */
    public OrderCart findOrderCartForInstant(String username) {
        Long cartSeq = null;
        for (OrderCart findCart : cartRepository.findOrderCartsByUserUsername(username)) {
            if (!findCart.isOrdered() && findCart.isInstant()) {
                cartSeq = findCart.getSeq();
            }
        }
        return cartRepository.findBySeq(cartSeq);
    }

    /**
     * 장바구니 구매용
     * 기존에 주문내역이 있는 유저 = 마지막 장바구니 isOrdered == True && isInstant == false 일 경우 새로 생성
     * 처음 주문하는 유저 = OrderCartList.isEmpty -> 새로 생성
     */
    public void getOrderCart(String username) {
        boolean check = true;
        var user = userRepository.findByUsername(username);

        if (!user.getOrderCartList().isEmpty()) { //장바구니가 이미 있엇으면 이걸로 ㅇㅇ
            for (OrderCart orderCart : user.getOrderCartList()) {
                if (!orderCart.isInstant()) {
                    check = orderCart.isOrdered();
                }
            }
            if (check) {
                createOrderCart(username);
            }
        }
        if (user.getOrderCartList().isEmpty()) { //첫 장바구니 생성
            createOrderCart(username);
        }
    }

    /**
     * 바로 구매용
     * 기존에 주문내역이 있는 유저 = 마지막 장바구니 isOrdered == True && isInstant == true 일 경우 새로 생성
     * 처음 주문하는 유저 = OrderCartList.isEmpty -> 새로 생성
     */
    public void getOrderCartForInstant(String username) {
        boolean check = true;
        var user = userRepository.findByUsername(username);

        if (!user.getOrderCartList().isEmpty()) { //장바구니가 이미 있엇으면 이걸로 ㅇㅇ
            Long seq = null;
            for (OrderCart orderCart : user.getOrderCartList()) {
                check = orderCart.isOrdered();
                seq = orderCart.getSeq();
            }
            if (check) {
                createOrderCartForInstant(username);
            } else {
                deleteOrderCart(seq);
                createOrderCartForInstant(username);
            }
        }
        if (user.getOrderCartList().isEmpty()) { //첫 장바구니 생성
            createOrderCartForInstant(username);
        }
    }

    /**
     * 장바구니 구매용
     * 장바구니 생성
     *
     * @param username
     */
    public void createOrderCart(String username) {
        var user = userRepository.findByUsername(username);
        var orderCart = new OrderCart(user);
        cartRepository.save(orderCart);
        log.info("OrderSerivce -> create Cart : OK  Cart = " + orderCart);
    }

    /**
     * 바로 구매용
     * 장바구니 생성
     *
     * @param username
     */
    public void createOrderCartForInstant(String username) {
        var user = userRepository.findByUsername(username);
        var orderCart = new OrderCart(user).toBuilder()
                .isInstant(true)
                .build();
        cartRepository.save(orderCart);
        log.info("OrderSerivce -> create Cart : OK  Cart = " + orderCart);
    }


    /**
     * 장바구니 구매용
     *
     * @param username
     * @return 합계액
     */
    public int findTotalPrice(String username) {
        var orderCart = findOrderCart(username);
        List<ProductCart> productCartList = orderCart.getProductCartList();
        return productCartList.stream()
                .mapToInt(i -> i.getProduct().getPrice() * i.getCount())
                .sum();
    }

    /**
     * 장바구니 구매용
     *
     * @param seq      상품 PK
     * @param username
     * @param count    구매하려는 도서 수
     */
    public void addProductToCart(long seq, String username, int count) {
        getOrderCart(username);

        var orderCart = findOrderCart(username);

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
        }
    }

    /**
     * 바로 구매용
     *
     * @param seq   상품 PK
     * @param count 구매하려는 도서 수
     */
    public void addProductToCartForInstant(long seq, String username, int count) {
        getOrderCartForInstant(username);

        var orderCart = findOrderCartForInstant(username);

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
        }
    }
}
