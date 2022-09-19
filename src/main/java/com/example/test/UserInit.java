package com.example.test;

import com.example.test.domain.model.User;
import com.example.test.domain.repository.UserRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserInit {
    private final UserRepository userRepository;

    public UserInit(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        User user = new User("fosong", "1356");
        userRepository.save(user);
    }
}
