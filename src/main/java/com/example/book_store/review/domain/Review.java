package com.example.book_store.review.domain;

import com.example.book_store.product.domain.Product;
import com.example.book_store.user.domain.User;
import jakarta.persistence.*;
import java.awt.*;
import java.security.Timestamp;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;
    @Column(columnDefinition = "Text")
    private String content;
    private double starRate;
    private Timestamp createdDate;
    private Timestamp modifiedDate;

    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;

    public Review() {
    }
}
