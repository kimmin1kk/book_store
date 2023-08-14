package com.example.book_store.order.repository;

import com.example.book_store.order.domain.OrderCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<OrderCart, Long> {
}
