package com.example.demo.domain.repository;

import com.example.demo.domain.model.User;

/**
 * @author Gerardo De Las Cuevas
 */
public interface UserRepository {
    User save(User user);
    User findByEmail(String email);
}
