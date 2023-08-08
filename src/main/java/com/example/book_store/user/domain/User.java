package com.example.book_store.user.domain;

import com.example.book_store.auth.domain.Authority;
import com.example.book_store.user.common.Grade;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long seq;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String nickname;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) default 'BRONZE'")
    private Grade grade;
    @Column(nullable = false)
    private Integer mileage;

    @Column(columnDefinition = "boolean default true")
    private boolean enabled = true;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Address> addressList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<CreditCard> cardList = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<Authority> authorities = new LinkedHashSet<>();
    @CreatedDate
    private Timestamp createdDate;
    @LastModifiedDate
    private Timestamp modifiedDate;

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "seq=" + seq +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", grade=" + grade +
                ", mileage=" + mileage +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", enabled=" + enabled +
                ", authorities=" + authorities +
                '}';
    }
}
