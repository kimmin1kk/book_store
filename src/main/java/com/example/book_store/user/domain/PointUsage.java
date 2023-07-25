package com.example.book_store.user.domain;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
public class PointUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    private int changedAmount;
    private String reasonChange;
    private Timestamp modifiedDate;
    @ManyToOne
    private User user;

    public PointUsage() {
    }
}
