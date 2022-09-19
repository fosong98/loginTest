package com.example.test.domain.service;

import com.example.test.domain.model.User;
import com.example.test.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {
    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> login(User u) {
        return userRepository.findById(u.getUserId())
                .filter((user)->user.getUserId().equals(u.getUserPassword()));
    }
}
