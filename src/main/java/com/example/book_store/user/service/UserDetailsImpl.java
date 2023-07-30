package com.example.book_store.user.service;

import com.example.book_store.auth.domain.Authority;
import com.example.book_store.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
@Slf4j
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final User user;

    private final Set<Authority> authorities;


    @Override
    @Transactional
    public Collection<? extends GrantedAuthority> getAuthorities() {
        log.info("UserDetailsImpl -> getAuthorities : OK");

        if (authorities.isEmpty()) {
            log.info("authorities is empty");
        }else {
            log.info("authorities size: {}", authorities.size());
        }
//        return authorities.stream()
//                .peek(authority -> log.info("Before Mapping User Role : {}", authority.getRole().toString()))
//                .map(authority -> (GrantedAuthority) () -> "ROLE_" + authority.getRole().toString())
//                .peek(grantedAuthority -> log.info("After Mapping User Role : {}", grantedAuthority.getAuthority()))
//                .collect(Collectors.toSet());
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().name()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isEnabled();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
