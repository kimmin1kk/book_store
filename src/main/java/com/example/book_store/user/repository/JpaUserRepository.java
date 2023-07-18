package com.example.book_store.user.repository;

import com.example.book_store.user.domain.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<User, Long> {
    @Override
    User save(User user);

    @Override
    @NonNull
    Optional<User> findById(Long seq);

//    @Override
//    void deleteById(long id);
}
