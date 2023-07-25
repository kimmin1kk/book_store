package com.example.book_store.user.domain;

import jakarta.persistence.*;
import lombok.NonNull;

import java.security.Timestamp;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;
    @NonNull
    private String postalCode;
    @NonNull
    private String defaultAddress;
    @NonNull
    private String detailAddress;
    @NonNull
    private Timestamp createdDate;
    @NonNull
    private Timestamp modifiedDate;
    @ManyToOne
    private User user;


    public Address() {
    }

}
