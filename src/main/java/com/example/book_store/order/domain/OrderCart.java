package com.example.book_store.order.domain;

import com.example.book_store.order.common.OrderState;
import com.example.book_store.user.domain.User;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
public class OrderCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    private OrderState orderState;

    private boolean isOrdered = false;

    @ManyToOne
    private User user;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderCart")
    private List<ProductCart> productCartList = new ArrayList<>();

    @CreatedDate
    private Timestamp createdDate;
    @LastModifiedDate
    private Timestamp modifiedDate;

    public OrderCart(User user) {
        this.user = user;
    }

    public OrderCart() {
    }

}
