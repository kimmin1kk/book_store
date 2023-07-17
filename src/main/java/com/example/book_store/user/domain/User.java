package com.example.book_store.user.domain;

import com.example.book_store.user.common.Grade;
import jakarta.persistence.*;
import lombok.NonNull;

import java.security.Timestamp;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private long seq;
    @NonNull
    private String id;
    @NonNull
    private String pw;
    @NonNull
    private String name;
    @NonNull
    private String nickname;
    @Enumerated(EnumType.STRING)
    @NonNull
    private Grade grade;
    @NonNull

    private int point;
    @NonNull

    private Timestamp createdDate;
    @NonNull

    private Timestamp modifiedDate;

    public User() {

    }

    public User(long seq, String id, String pw, String name, String nickname, Grade grade, int point, Timestamp createdDate, Timestamp modifiedDate) {
        this.seq = seq;
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.nickname = nickname;
        this.grade = grade;
        this.point = point;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
