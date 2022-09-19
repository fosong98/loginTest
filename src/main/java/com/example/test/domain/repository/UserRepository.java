package com.example.test.domain.repository;

import com.example.test.domain.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
    private static Map<Long, User> coll = new ConcurrentHashMap<>();
    private static long idSequence = 0;

    public void save(User user) {
        coll.put(idSequence++, user);
    }

    public List<User> findAll() {
        return coll.values().stream().toList();
    }

    public Optional<User> findById(String userId) {
        return findAll().stream()
                .filter((user) -> user.getUserId().equals(userId))
                .findFirst();
    }

    public void clear() {
        coll.clear();
    }
}
