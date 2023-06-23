package ru.skypro.lessons.springboot.weblibrary1.security;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AuthUser, Long> {
    AuthUser findByUsername(String username);
}
