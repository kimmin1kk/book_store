package com.example.book_store.order.domain;

import com.example.book_store.order.common.OrderState;
import com.example.book_store.user.domain.User;
import jakarta.persistence.*;
import lombok.*;
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
@Builder(toBuilder = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
public class OrderCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    private boolean isInstant = false;
    private boolean isOrdered = false;

    private String postalCode;
    private String defaultAddress;
    private String detailAddress;

    private String cardNumber;
    private String cardType;

    @ManyToOne(cascade = CascadeType.DETACH)
    private User user;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderCart", cascade = CascadeType.REMOVE, orphanRemoval = true)
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
