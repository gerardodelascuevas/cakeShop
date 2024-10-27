package com.example.demo.domain.service;

import com.example.demo.domain.model.Client;

import java.util.List;

/**
 * @author Gerardo De Las Cuevas
 */
public interface ClientDomainService {
    Client createClient(Client client);
    List<Client> getAllClients();
}
