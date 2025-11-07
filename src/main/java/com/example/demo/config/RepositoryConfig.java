package com.example.demo.config;

import com.example.demo.repository.*;
import com.example.demo.repository.jpa.UserJpaRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;  // for symbol Primary


/*
Fixed this by adding @Primary to userRepository()
Hibernate: drop table if exists user cascade
2025-11-07T00:09:40.624+07:00 ERROR 4252 --- [           main] .SchemaDropperImpl$DelayedDropActionImpl : HHH000478: Unsuccessful: drop table if exists user cascade
2025-11-07T00:09:40.656+07:00 ERROR 4252 --- [           main] o.s.b.d.LoggingFailureAnalysisReporter   : 

***************************
APPLICATION FAILED TO START
***************************
*/

@Configuration
public class RepositoryConfig {

    @Value("${app.repo.type:inmemory}")
    private String repoType;

    private final UserRepositoryInMemory inMemoryRepo;
    private final UserJpaRepository jpaRepo;

    public RepositoryConfig(UserRepositoryInMemory inMemoryRepo, UserJpaRepository jpaRepo) {
        this.inMemoryRepo = inMemoryRepo;
        this.jpaRepo = jpaRepo;
    }

    // Mark your factory bean as the primary source for UserRepository.
    @Bean
    @Primary  // 👈 Add this line
    public UserRepository userRepository() {
        return switch (repoType.toLowerCase()) {
            // set project to use InMemoryRepo
            // case "jpa" -> jpaRepo;
            // default -> inMemoryRepo;

            // set project to use jpaRepo
            default -> jpaRepo;
        };
    }
}
