package com.example.book_store.product.service;

import com.example.book_store.product.common.Category;
import com.example.book_store.product.domain.Product;
import com.example.book_store.product.repository.ProductRepository;
import com.example.book_store.product.repository.SearchProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final SearchProductRepository searchProductRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, SearchProductRepository searchProductRepository) {
        this.productRepository = productRepository;
        this.searchProductRepository = searchProductRepository;
    }

    public Optional<Product> findProductBySeq(Long seq) {
        return productRepository.findById(seq);
    }

    public List<Product> productList() {
        return productRepository.findAll();
    }

    public List<Product> searchProductList(String keyword, Category category) {
        List<Product> productList;
        if (keyword != null && !keyword.isEmpty() && category != Category.ALL) {
            // 키워드와 카테고리 모두 검색 조건으로 사용할 때
            productList = searchProductRepository.findProductByNameContainingAndCategory(keyword, category);
        } else if (category != Category.ALL) {
            // 카테고리만 검색 조건으로 사용할 때
            productList = searchProductRepository.findProductByCategory(category);
        } else if (keyword != null && category == Category.ALL) {
            // 키워드만 검색 조건으로 사용할 때
            productList = searchProductRepository.findProductByNameContaining(keyword);
        } else {
            // 검색 조건이 없을 때
            productList = productRepository.findAll();
        }
        return productList;
    }

}
