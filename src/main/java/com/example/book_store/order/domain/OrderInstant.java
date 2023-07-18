package com.example.book_store.order.domain;

import jakarta.persistence.*;
import lombok.NonNull;

import java.security.Timestamp;

@Entity
public class OrderInstant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;
    @NonNull
    private Timestamp createdDate;
    @NonNull
    private Timestamp modifiedDate;

    public OrderInstant() {
    }

    public OrderInstant(long seq, @NonNull Timestamp createdDate, @NonNull Timestamp modifiedDate) {
        this.seq = seq;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
