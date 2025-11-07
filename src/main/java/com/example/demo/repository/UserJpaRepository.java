package com.example.demo.repository.jpa;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository
@Repository("jpaRepo")
public interface UserJpaRepository extends JpaRepository<User, Long>, UserRepository {
    // no extra methods needed, inherits removeById() etc
}
