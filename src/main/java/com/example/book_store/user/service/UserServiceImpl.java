package com.example.book_store.user.service;

import com.example.book_store.user.common.RegistrationForm;
import com.example.book_store.user.common.UserDto;
import com.example.book_store.auth.domain.Authority;
import com.example.book_store.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDto findUserInfo(Principal principal) {

        return null;
    }
    @Override
    public void processRegistration(RegistrationForm form) {
        Authority auth = new Authority();
        auth.setUser(userRepository.save(form.toUser(passwordEncoder)));
        log.info("UserServiceImpl/processRegistration : OK");
    }
    @Override
    public List<UserDto> findAllUsers() {
        return null;
    }
}
