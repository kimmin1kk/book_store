package com.example.book_store.product.service;

import com.example.book_store.product.domain.Product;
import com.example.book_store.product.repository.JpaProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private JpaProductRepository jpaProductRepository;
    @Autowired
    public ProductService(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }


    public Product create(Product product) {
        return jpaProductRepository.save(product);
    }

    public Optional<Product> read(Long id) {
        return jpaProductRepository.findById(id);
    }

    public void delete(Long id) {
        jpaProductRepository.deleteById(id);
    }
}
