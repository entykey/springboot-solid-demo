package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service  // 🔥 This annotation is critical for Spring to detect it as a Bean
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @Override
    public Optional<User> getUser(Long id) {
        return repo.findById(id);
    }

    @Override
    public User saveUser(User user) {
        return repo.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        repo.deleteById(id);
    }
}



// package com.example.demo.service;

// import com.example.demo.model.User;
// import com.example.demo.repository.UserRepository;
// import org.springframework.stereotype.Service;
// import java.util.List;
// import java.util.Optional;

// @Service // ✅ This is critical
// public class UserServiceImpl implements UserService {
//     private final UserRepository repo;

//     public UserServiceImpl(UserRepository repo) {
//         this.repo = repo;
//     }

//     @Override
//     public List<User> getAllUsers() {
//         return repo.findAll();
//     }

//     @Override
//     public Optional<User> getUser(Long id) {
//         return repo.findById(id);
//     }

//     @Override
//     public User saveUser(User user) {
//         return repo.save(user);
//     }

//     @Override
//     public void deleteUser(Long id) {
//         // repo.delete(id);    // cannot find symbol: method delete(java.lang.Long)
//         repo.deleteById(id);    // at `src\main\java\com\example\demo\repository\UserRepository.java`
//     }
// }
