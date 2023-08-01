package com.example.book_store.home.service;

import com.example.book_store.product.domain.Product;
import com.example.book_store.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {

    private final ProductRepository productRepository;

    @Autowired
    public HomeService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> productList() {
        return productRepository.findAll();
    }

}
