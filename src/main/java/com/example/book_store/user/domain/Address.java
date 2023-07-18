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

    public Address() {
    }

    public Address(long seq, @NonNull String postalCode, @NonNull String defaultAddress, @NonNull String detailAddress, @NonNull Timestamp createdTime, @NonNull Timestamp modifiedTime) {
        this.seq = seq;
        this.postalCode = postalCode;
        this.defaultAddress = defaultAddress;
        this.detailAddress = detailAddress;
        this.createdDate = createdTime;
        this.modifiedDate = modifiedTime;
    }
}
