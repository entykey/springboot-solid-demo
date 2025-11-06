package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAll() {
        return service.getAllUsers();
    }
    

    // will cause failed in test (due to exception thrown)
    // @GetMapping("/{id}")
    // public User getById(@PathVariable Long id) {
    //     return service.getUser(id)
    //             .orElseThrow(() -> new RuntimeException("User not found"));
    // }

    // No raw exceptions, real 404 Not Found behavior
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return service.getUser(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return service.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteUser(id);
    }
}
