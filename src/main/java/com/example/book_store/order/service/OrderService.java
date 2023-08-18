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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final CartRepository cartRepository;
    private final ProductCartRepository productCartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

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
     * OrderHistoryDto(DTO) 반환
     */
    @Transactional
    public void processOrder(OrderCart orderCart) {
        List<ProductCart> productCartList = new ArrayList<>();
        if (checkCart(orderCart)) { //재고량 검사 과정
            for (ProductCart productCart : orderCart.getProductCartList()) { // 재고량 -- 하는 과정
                Product product = productCart.getProduct();
                product.setQuantity(product.getQuantity() - productCart.getCount());
                productRepository.save(product);
                productCartList.add(productCart);
            }

        }
        // Denied
    }

    /**
     * processOrder에서 모든 주문 예외를 처리한 뒤 결제 확정 짓는 로직을 짜야함
     * 여기서 장바구니를 삭제
     */
    public void confirmOrder() {

    }


}
