package com.example.book_store.common;

import org.apache.ibatis.javassist.NotFoundException;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
