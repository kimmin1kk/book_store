package com.example.book_store.order.service;

import com.example.book_store.order.common.OrderPageForm;
import com.example.book_store.order.common.OrderState;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.book_store.order.common.OrderState.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final CartRepository cartRepository;
    private final ProductCartRepository productCartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderCartService orderCartService;

    /**
     * 상품 하나의 유효성을 검사하는 로직
     * checkCart 메서드 내부에서 사용
     * ProductCart Seq 넣어야함
     */
    public boolean checkQuantity(Long seq) {
        Optional<ProductCart> productCartOptional = productCartRepository.findById(seq);
        return productCartOptional.filter(productCart -> productCart.getCount() <= productCart.getProduct().getQuantity()).isPresent();
    }

    /**
     * 리스트(orderCart -> productCartList)를 넣어 내부의 모든 상품의 유효성을 검사하는 로직
     */

    public boolean checkCart(OrderCart orderCart) {
        for (ProductCart product : orderCart.getProductCartList()) {
            if (!checkQuantity(product.getSeq())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Param으로 OrderCart를 받아 검사하는 로직
     * confirmOrder 메서드 내부에서 사용
     */
    @Transactional
    public void processOrder(OrderCart orderCart) {
        List<ProductCart> productCartList = new ArrayList<>();
        for (ProductCart productCart : orderCart.getProductCartList()) { // 재고량 -- 하는 과정
            Product product = productCart.getProduct();
            product.setQuantity(product.getQuantity() - productCart.getCount());
            productRepository.save(product);
            productCartList.add(productCart);
        }

    }

    /**
     * checkCart로 먼저 주문이 가능한 상태인지 확인
     * processOrder에서 값이 정상인지 확인
     */
    @Transactional
    public void confirmOrder(OrderPageForm orderPageForm, OrderCart orderCart) {
        if (checkCart(orderCart)) {
            processOrder(orderCart);
            OrderCart modifiedOrderCart = orderCart.toBuilder()
                    .orderState(PREPARING)
                    .postalCode(orderPageForm.getPostalCode())
                    .defaultAddress(orderPageForm.getDefaultAddress())
                    .detailAddress(orderPageForm.getDetailAddress())
                    .cardNumber(orderPageForm.getCardNumber())
                    .cardType(orderPageForm.getCardType())
                    .isOrdered(true)
                    .build();
            cartRepository.save(modifiedOrderCart);
        }

    }

    /**
     * isOrdered == True인 장바구니 리스트를 찾는 로직.
     *
     * @param username (Principal.getName())
     * @return List<OrderCart> orderedCarts
     */
    public List<OrderCart> getOrderedCarts(String username) {
        var user = userRepository.findByUsername(username);
        List<OrderCart> orderedCarts = new ArrayList<>();
        for (OrderCart selectOrderedCart : user.getOrderCartList()) {
            if (selectOrderedCart.isOrdered()) {
                orderedCarts.add(selectOrderedCart);
            }
        }
        return orderedCarts;
    }


}
