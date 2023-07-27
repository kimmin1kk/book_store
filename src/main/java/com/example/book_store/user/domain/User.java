package com.example.book_store.user.domain;

import com.example.book_store.auth.domain.Authority;
import jakarta.persistence.*;
import lombok.Data;
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
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String nickname;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "BRONZE")
    private Grade grade;
    @Column(nullable = false)
    private Integer point;
    @CreatedDate
    private Timestamp createdDate;
    @LastModifiedDate
    private Timestamp modifiedDate;

    @Column(columnDefinition = "true")
    private boolean enabled = true;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Authority> authorities = new LinkedHashSet<>();
    public User() {

    }

}
