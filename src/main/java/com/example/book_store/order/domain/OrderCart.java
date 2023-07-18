package com.example.book_store.order.domain;

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

    public OrderCart() {
    }

    public OrderCart(long seq, @NonNull int totalPrice, Timestamp createdDate, Timestamp modifiedDate) {
        this.seq = seq;
        this.totalPrice = totalPrice;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
