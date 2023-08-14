package com.example.book_store.order.domain;

import com.example.book_store.product.domain.Product;
import com.example.book_store.user.domain.User;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;


@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
public class OrderInstant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;
    @CreatedDate
    private Timestamp createdDate;
    @LastModifiedDate
    private Timestamp modifiedDate;
    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;
    public OrderInstant() {
    }
}
