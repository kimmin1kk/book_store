package com.example.book_store.user.service;

import com.example.book_store.user.domain.User;
import com.example.book_store.user.repository.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private JpaUserRepository jpaUserRepository;

    @Autowired
    public UserService(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    public User create(User user) {
        return jpaUserRepository.save(user);
    }

    public Optional<User> read(Long id) {
        return jpaUserRepository.findById(id);
    }

    public void delete(Long id) {
        jpaUserRepository.deleteById(id);

    }

}
