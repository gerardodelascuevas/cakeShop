package com.example.demo.domain.repository;

import com.example.demo.domain.model.Client;

import java.util.List;
import java.util.Optional;

/**
 * @author Gerardo De Las Cuevas
 */
public interface ClientRepository {
    Client save(Client client);
    List<Client> findAll();
    Optional<Client> findById(Long clientId);
}
