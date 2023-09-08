package com.example.book_store.order.domain;

import com.example.book_store.product.domain.Product;
import jakarta.persistence.*;
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
public class ProductCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @ManyToOne
    @JoinColumn(name = "ordercart_seq")
    private OrderCart orderCart;

    @ManyToOne
    @JoinColumn(name = "product_seq")
    private Product product;


    private Integer count;


    @CreatedDate
    private Timestamp createdDate;
    @LastModifiedDate
    private Timestamp modifiedDate;

    public ProductCart() {
    }

    public ProductCart(OrderCart orderCart, Product product, int count) {
        this.orderCart = orderCart;
        this.product = product;
        this.count = count;
    }
}
