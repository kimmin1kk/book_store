package com.example.book_store.product.repository;

import com.example.book_store.product.common.Category;
import com.example.book_store.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductByNameContaining(String keyword);
    List<Product> findProductByCategory(Category category);
    List<Product> findProductByNameContainingAndCategory(String keyword,Category category);

}
