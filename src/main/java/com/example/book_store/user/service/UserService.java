package com.example.book_store.user.service;

import com.example.book_store.user.common.RegistrationForm;
import com.example.book_store.user.common.UserDto;
import com.example.book_store.user.domain.PointUsage;
import com.example.book_store.user.domain.User;

import java.security.Principal;
import java.util.List;

public interface UserService {
    UserDto findUserInfo(Principal principal);

    void processRegistration(RegistrationForm form);

    List<UserDto> findAllUsers();

}
