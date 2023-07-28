package com.example.book_store.user.common;

import com.example.book_store.user.domain.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.example.book_store.user.domain.Grade.BRONZE;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String nickname;
    private String name;

    public User toUser(PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setNickname(nickname);
        user.setName(name);
        user.setGrade(BRONZE);
        user.setMileage(0);
        user.setEnabled(true);

        return user;
    }
}
