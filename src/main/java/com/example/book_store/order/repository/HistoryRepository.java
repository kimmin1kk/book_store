package com.example.book_store.order.repository;

import com.example.book_store.order.domain.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<OrderHistory, Long> {
}
