package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository("inMemoryRepo")
public class UserRepositoryInMemory implements UserRepository {
    private final Map<Long, User> db = new HashMap<>();
    private long seq = 1;

    @Override
    public List<User> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) user.setId(seq++);
        db.put(user.getId(), user);
        return user;
    }

    @Override
    public void deleteById(Long id) {
        db.remove(id);
    }
}