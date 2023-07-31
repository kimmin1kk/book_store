package com.example.book_store.user.service;

import com.example.book_store.auth.repository.AuthorityRepository;
import com.example.book_store.user.common.RegistrationForm;
import com.example.book_store.user.common.UserDto;
import com.example.book_store.auth.domain.Authority;
import com.example.book_store.user.domain.Role;
import com.example.book_store.user.domain.User;
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
    private final AuthorityRepository authorityRepository;

    @Override
    public UserDto findUserInfo(Principal principal) {

        return null;
    }
    @Override
    @Transactional
    public void processRegistration(RegistrationForm form) {
        log.info("UserServiceImpl -> processRegistration : OK");
        var auth = new Authority();
        auth.setRole(Role.ROLE_USER);

        var newUser = form.toUser(passwordEncoder);
        auth.setUser(newUser);

        var savedUser = userRepository.save(newUser);
        savedUser.getAuthorities().add(auth);

        authorityRepository.save(auth);

    }
    @Override
    public List<UserDto> findAllUsers() {
        return null;
    }
}
