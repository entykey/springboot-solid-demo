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

    // 🟢 Lấy tất cả user
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
    // 🟣 Lấy user theo ID
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return service.getUser(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    // 🟡 Tạo user mới
    @PostMapping
    public User create(@RequestBody User user) {
        return service.saveUser(user);
    }

    // 🔵 Cập nhật user
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User updatedUser) {
        return service.getUser(id)
                .map(existing -> {
                    existing.setName(updatedUser.getName());
                    existing.setEmail(updatedUser.getEmail());
                    return service.saveUser(existing);
                })
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    // 🔴 Xóa user
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (service.getUser(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        service.deleteUser(id);
    }
}
