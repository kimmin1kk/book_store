package com.example.book_store.order.repository;

import com.example.book_store.order.domain.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCartRepository extends JpaRepository<ProductCart, Long> {

}
