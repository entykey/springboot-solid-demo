package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository mockRepo;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        mockRepo = Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl(mockRepo);
    }

    @Test
    void testSaveUser() {
        User user = new User();
        user.setName("John");
        user.setEmail("john@example.com");

        when(mockRepo.save(any(User.class))).thenReturn(user);

        User result = userService.saveUser(user);
        assertEquals("John", result.getName());
        verify(mockRepo, times(1)).save(any(User.class));
    }

    @Test
    void testGetUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Jane");

        when(mockRepo.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUser(1L);
        assertTrue(result.isPresent());
        assertEquals("Jane", result.get().getName());
    }

    @Test
    void testGetAllUsers() {
        when(mockRepo.findAll()).thenReturn(List.of(new User(), new User()));

        List<User> result = userService.getAllUsers();
        assertEquals(2, result.size());
        verify(mockRepo, times(1)).findAll();
    }

    @Test
    void testDeleteUser() {
        doNothing().when(mockRepo).deleteById(1L);

        userService.deleteUser(1L);

        verify(mockRepo, times(1)).deleteById(1L);
    }
}
