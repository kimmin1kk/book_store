package com.example.book_store.admin.service;

import com.example.book_store.admin.common.ProductAddForm;
import com.example.book_store.product.domain.Product;
import com.example.book_store.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public void processingAddProduct(ProductAddForm productAddForm) {
        log.info("AdminServiceImpl -> processingAddProduct : OK");
        var newProduct = productAddForm.addProduct();

        log.info("newProduct is " + newProduct.toString());
        productRepository.save(newProduct);
    }
}
