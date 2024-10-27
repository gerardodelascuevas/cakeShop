package com.example.demo.infraestructure.persistence.repository;

import com.example.demo.domain.model.Client;
import com.example.demo.domain.repository.ClientRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Gerardo De Las Cuevas
 */
@Repository
public interface ClientJpaRepository extends JpaRepository<Client, Long>, ClientRepository {

}
