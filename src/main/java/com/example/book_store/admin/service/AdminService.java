package com.example.book_store.admin.service;

import com.example.book_store.admin.common.ProductAddForm;
import com.example.book_store.product.domain.Product;
import com.example.book_store.product.repository.ProductRepository;
import com.example.book_store.user.domain.User;
import com.example.book_store.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {


    private ProductRepository productRepository;
    private UserRepository userRepository;

    @Autowired
    public AdminService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void processingAddProduct(ProductAddForm productAddForm) {
        log.info("AdminServiceImpl -> processingAddProduct : OK");
        var newProduct = productAddForm.addProduct();

        log.info("newProduct is " + newProduct.toString());
        productRepository.save(newProduct);
    }

    public List<User> userList() {
        return userRepository.findAll();
    }

}
