package com.example.book_store.user.service;

import com.example.book_store.user.common.RegistrationForm;
import com.example.book_store.user.common.UserDto;
import com.example.book_store.auth.domain.Authority;
import com.example.book_store.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;



    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto findUserInfo(Principal principal) {
        return null;
    }
    @Override
    public void processRegistration(RegistrationForm form) {
        Authority auth = new Authority();
        auth.setUser(userRepository.save(form.toUser(passwordEncoder)));

    }
    @Override
    public List<UserDto> findAllUsers() {
        return null;
    }
}
