package com.example.book_store.user.common;

import com.example.book_store.user.domain.Grade;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private long seq;
    private String username;
    private String password;
    private String name;
    private String nickname;
    private Grade grade;
    private Integer point;
}
