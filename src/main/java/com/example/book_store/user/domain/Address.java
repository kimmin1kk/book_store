package com.example.book_store.user.domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.security.Timestamp;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;
    @Column(nullable = false)
    private String postalCode;
    @Column(nullable = false)
    private String defaultAddress;
    @Column(nullable = false)
    private String detailAddress;
    @CreatedDate
    private Timestamp createdDate;
    @LastModifiedDate
    private Timestamp modifiedDate;
    @ManyToOne
    private User user;


    public Address() {
    }

}
