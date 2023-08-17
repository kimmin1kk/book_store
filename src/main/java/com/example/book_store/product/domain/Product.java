package com.example.book_store.product.domain;

import com.example.book_store.order.domain.OrderCart;
import com.example.book_store.product.common.Category;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(nullable = false)
    private String author;

    @CreatedDate
    private Timestamp createdDate;
    @LastModifiedDate
    private Timestamp modifiedDate;

    public Product() {
    }

    @Builder
    public Product(String name, Integer quantity, Integer price, Category category, String author) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
        this.author = author;
    }
}
