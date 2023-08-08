package com.example.book_store.user.service;

import com.example.book_store.auth.repository.AuthorityRepository;
import com.example.book_store.user.common.RegistrationForm;
import com.example.book_store.auth.domain.Authority;
import com.example.book_store.user.common.Role;
import com.example.book_store.user.common.UserInformationDto;
import com.example.book_store.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    @Transactional
    public void processRegistration(RegistrationForm form) {
        log.info("UserService -> processRegistration : OK");
        var auth = new Authority();
        auth.setRole(Role.ROLE_USER);

        var newUser = form.toUser(passwordEncoder);
        auth.setUser(newUser);

        var savedUser = userRepository.save(newUser);
        savedUser.getAuthorities().add(auth);

        authorityRepository.save(auth);
    }

    public UserInformationDto findUserInformationByUsername(String username) {
        var user =userRepository.findByUsername(username);
        return UserInformationDto.builder()
                .name(user.getName())
                .nickname(user.getNickname())
                .grade(user.getGrade())
                .mileage(user.getMileage())
                .createdDate(user.getCreatedDate())
                .build();
    }
}

