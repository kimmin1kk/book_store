package com.example.book_store.user.domain;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    private String number;

    private String validation;

    @Enumerated(EnumType.STRING)
    private Type type;
    private Timestamp createdTime;
    private Timestamp modifiedTime;
    @ManyToOne
    private User user;
    public CreditCard() {
    }
}
