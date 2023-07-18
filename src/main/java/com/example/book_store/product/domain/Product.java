package com.example.book_store.product.domain;

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
    @ColumnDefault("0")
    private int price;
    @NonNull
    @Enumerated(EnumType.STRING)
    private Category category;
    @NonNull
    private String author;
    @NonNull
    private double rate;
    @NonNull
    private Timestamp createdDate;
    @NonNull
    private Timestamp modifiedDate;

    public Product() {
    }

    public Product(long seq, @NonNull String name, @NonNull String quantity, @NonNull int price, @NonNull Category category, @NonNull String author, @NonNull double rate, @NonNull Timestamp createdDate, @NonNull Timestamp modifiedDate) {
        this.seq = seq;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
        this.author = author;
        this.rate = rate;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
