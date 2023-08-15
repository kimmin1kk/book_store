package com.example.book_store.order.repository;

import com.example.book_store.order.domain.OrderCart;
import com.example.book_store.order.domain.ProductCart;
import com.example.book_store.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCartRepository extends JpaRepository<ProductCart, Long> {
    ProductCart findByOrderCartAndProduct(OrderCart orderCart, Product product);

}
