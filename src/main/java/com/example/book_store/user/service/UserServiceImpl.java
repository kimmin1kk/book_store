package com.example.book_store.user.service;

import com.example.book_store.user.common.RegistrationForm;
import com.example.book_store.user.common.UserDto;
import com.example.book_store.auth.domain.Authority;
import com.example.book_store.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void processRegistration(RegistrationForm form) {
        log.info("UserServiceImpl/processRegistration : OK");
        Authority auth = new Authority();
        auth.setUser(userRepository.save(form.toUser(passwordEncoder)));
    }
    @Override
    public List<UserDto> findAllUsers() {
        return null;
    }
}
