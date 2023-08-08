package com.example.book_store.order.domain;

import com.example.book_store.product.domain.Product;
import com.example.book_store.user.domain.User;
import jakarta.persistence.*;
import lombok.NonNull;
import org.hibernate.annotations.ColumnDefault;

import java.security.Timestamp;

@Entity
public class OrderCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;
    @ColumnDefault("0")
    private int totalPrice;
    @NonNull
    private Timestamp createdDate;
    @NonNull
    private Timestamp modifiedDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;


    public OrderCart() {
    }

}
