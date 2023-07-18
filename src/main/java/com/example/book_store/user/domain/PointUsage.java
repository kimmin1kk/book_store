package com.example.book_store.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.security.Timestamp;

@Entity
public class PointUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    private int changedAmount;
    private String reasonChange;
    private Timestamp modifiedDate;

}
