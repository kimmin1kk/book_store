package com.example.book_store.product.domain;

import com.example.book_store.order.domain.OrderCart;
import com.example.book_store.product.common.Category;
import jakarta.persistence.*;
import lombok.NonNull;
import org.hibernate.annotations.ColumnDefault;

import java.security.Timestamp;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;
    @NonNull
    private String name;
    @NonNull
    private String quantity;
    @NonNull
    private Integer price;
    @NonNull
    @Enumerated(EnumType.STRING)
    private Category category;
    @NonNull
    private String author;
    @NonNull
    private Double rate;
    @NonNull
    private Timestamp createdDate;
    @NonNull
    private Timestamp modifiedDate;

    @ManyToOne
    private OrderCart orderCart;

    public Product() {
    }
}
