package com.example.book_store.auth.repository;

import com.example.book_store.auth.domain.Authority;
import com.example.book_store.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<List<Authority>> findAuthorities(User user);

    void deleteUser(User user);
}
