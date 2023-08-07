package com.example.book_store.product.service;

import com.example.book_store.product.domain.Product;
import com.example.book_store.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> findProductBySeq(Long seq) {
        return productRepository.findById(seq);
    }

    public List<Product> productList() {
        return productRepository.findAll();
    }


}
