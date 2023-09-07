package com.example.book_store.admin.service;

import com.example.book_store.admin.common.ProductAddForm;
import com.example.book_store.order.common.OrderState;
import com.example.book_store.order.domain.OrderCart;
import com.example.book_store.order.repository.CartRepository;
import com.example.book_store.product.domain.Product;
import com.example.book_store.product.repository.ProductRepository;
import com.example.book_store.user.domain.User;
import com.example.book_store.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    @Transactional
    public void processingAddProduct(ProductAddForm productAddForm) {
        log.info("AdminServiceImpl -> processingAddProduct : OK");
        var newProduct = productAddForm.addProduct();

        log.info("newProduct is " + newProduct.toString());
        productRepository.save(newProduct);
    }

    public Product updateProduct(Long seq, Product updatedProduct) {
        Optional<Product> productOptional = productRepository.findById(seq);

        if (productOptional.isPresent()) {
            Product existingProduct = productOptional.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setQuantity(updatedProduct.getQuantity());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setCategory(updatedProduct.getCategory());
            existingProduct.setAuthor(updatedProduct.getAuthor());

            return productRepository.save(existingProduct);
        } else {
            throw new RuntimeException("ser not found with seq: " + seq);
        }
    }

    public void deleteProductBySeq(long seq) {
        productRepository.deleteById(seq);
    }

    public OrderCart updateOrderState(Long seq, OrderState orderState) {
        Optional<OrderCart> orderCartOptional = cartRepository.findById(seq);
        if (orderCartOptional.isPresent()) {
            OrderCart existingOrderCart = orderCartOptional.get();
            existingOrderCart.setOrderState(orderState);
            return cartRepository.save(existingOrderCart);
        } else {
            throw new RuntimeException("ser not found with seq: " + seq);
        }
    }

    public List<User> userList() {
        return userRepository.findAll();
    }

    public Optional<User> findUserBySeq(Long seq) {
        return userRepository.findById(seq);
    }

    public User updateUser(Long seq, User updatedUser) {
        Optional<User> userOptional = userRepository.findById(seq);

        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            existingUser.setNickname(updatedUser.getNickname());
            existingUser.setName(updatedUser.getName());
            existingUser.setGrade(updatedUser.getGrade());
            existingUser.setMileage(updatedUser.getMileage());

            return userRepository.save(existingUser);

        } else {
            throw new RuntimeException("ser not found with seq: " + seq);
        }
    }

    public void deleteUserBySeq(Long seq) {
        userRepository.deleteById(seq);
    }

}
