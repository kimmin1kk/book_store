package com.example.book_store.order.repository;

import com.example.book_store.order.domain.OrderCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<OrderCart, Long> {
    OrderCart findByUserUsername(String username);

    OrderCart findBySeq(Long seq);
    List<OrderCart> findOrderCartsByUserUsername(String username);
}
