package com.example.book_store.user.repository;

import com.example.book_store.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<User, Long> {
    @Override
    User save(User user);

    @Override
    Optional<User> findById(Long id);

//    @Override
//    void deleteById(long id);
}
