package com.example.demo.repository.jpa;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long>, UserRepository {
    // no extra methods needed, inherits removeById() etc
}




// package com.example.demo.repository;

// import com.example.demo.model.User;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// @Repository("jpaRepo")
// public interface UserJpaRepository extends JpaRepository<User, Long>, UserRepository {
// }
