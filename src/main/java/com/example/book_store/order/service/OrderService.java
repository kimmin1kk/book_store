package com.example.book_store.order.service;

import com.example.book_store.order.repository.CartRepository;
import com.example.book_store.order.repository.HistoryRepository;
import com.example.book_store.product.repository.ProductRepository;
import com.example.book_store.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final HistoryRepository historyRepository;
    private final UserRepository userRepository;

}
