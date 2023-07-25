package com.example.book_store.user.domain;

import com.example.book_store.user.common.Grade;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.security.Timestamp;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String name;
    @NonNull
    private String nickname;
    @Enumerated(EnumType.STRING)
    @NonNull
    private Grade grade;
    @NonNull
    private Integer point;
    @CreatedDate
    private Timestamp createdDate;
    @LastModifiedDate
    private Timestamp modifiedDate;

    private boolean enabled = true;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Authority> authorities = new LinkedHashSet<>();
    public User() {

    }

}
