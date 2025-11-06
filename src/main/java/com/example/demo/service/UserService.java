package com.example.demo.service;

import com.example.demo.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUser(Long id);
    User saveUser(User user);   // also for add
    void deleteUser(Long id);
}
