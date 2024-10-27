package com.example.demo.infraestructure.persistence.repository;

import com.example.demo.domain.model.User;
import com.example.demo.domain.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Gerardo De Las Cuevas
 */
@Repository
public interface UserJpaRepository extends JpaRepository<User, Long>, UserRepository {

}
