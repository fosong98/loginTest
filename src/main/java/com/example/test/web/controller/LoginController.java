package com.example.test.web.controller;

import com.example.test.domain.model.User;
import com.example.test.domain.repository.UserRepository;
import com.example.test.domain.service.LoginService;
import com.example.test.web.session.SessionConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
public class LoginController {
    private final UserRepository userRepository;
    private final LoginService loginService;

    public LoginController(UserRepository userRepository, LoginService loginService) {
        this.userRepository = userRepository;
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletRequest request, @RequestBody User user) {
        System.err.println("x");
        String data = "";

        Optional<User> optionalUser = loginService.login(user);

        if (optionalUser.isEmpty()) {
            System.out.println("x");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConstants.sessionId, optionalUser.get().getUserId());

        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/data")
    public ResponseEntity<?> data(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*/*");

        if (session == null) {
            return new ResponseEntity<>(headers, HttpStatus.FORBIDDEN);
        }

        String userId = (String) session.getAttribute(SessionConstants.sessionId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            return new ResponseEntity<>("403" ,headers, HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity("Very Good, " + userId, headers, HttpStatus.OK);
    }

}
