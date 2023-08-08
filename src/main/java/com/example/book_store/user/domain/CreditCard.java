package com.example.book_store.user.domain;

import com.example.book_store.user.common.Type;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
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
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @Column(nullable = false, unique = true)
    private String number;
    @Column(nullable = false)
    private String validation;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;
    @CreatedDate
    private Timestamp createdTime;
    @LastModifiedDate
    private Timestamp modifiedTime;
    @ManyToOne
    private User user;

    public CreditCard() {
    }
}
