package com.example.book_store.auth.domain;

import com.example.book_store.user.domain.Role;
import com.example.book_store.user.domain.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Enumerated(EnumType.STRING)
    private Role role;
}
