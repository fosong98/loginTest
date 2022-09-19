package com.example.test.domain.model;

public class User {
    private String userId;
    private String userPassword;

    public User(String userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    @Override
    public String toString() {
        return String.format("{\'userId\': %s, \'userPassword\': %s}", userId, userPassword);
    }
}
