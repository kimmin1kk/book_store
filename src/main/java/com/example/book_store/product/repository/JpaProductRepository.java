package com.example.book_store.product.repository;

import com.example.book_store.product.domain.Product;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface JpaProductRepository extends JpaRepository<Product, Long> {

    @Override
    Product save(Product product);

    @Override
    @NonNull
    Optional<Product> findById(Long seq);
}
