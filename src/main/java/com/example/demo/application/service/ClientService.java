package com.example.demo.application.service;

import com.example.demo.application.dto.ClientDto;
import com.example.demo.domain.model.Client;
import com.example.demo.domain.repository.ClientRepository;
import com.example.demo.domain.service.ClientDomainService;
import com.example.demo.exception.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Gerardo De Las Cuevas
 */
@Service
public class ClientService implements ClientDomainService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, GlobalExceptionHandler error) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public List<ClientDto> getAllClientsAsDto() {
        List<Client> allClients = clientRepository.findAll();
        return allClients.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ClientDto convertToDto(Client client) {
        ClientDto dto = new ClientDto();
        dto.setId(client.getId());
        dto.setFirstName(client.getFirstName());
        dto.setEmail(client.getEmail());
        return dto;
    }

    public ClientDto storeClient(ClientDto dto){

        if (dto.getFirstName() == null || dto.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First name is required.");
        }
        if (dto.getLastName() == null || dto.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Last name is required.");
        }
        if (dto.getEmail() == null || dto.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required.");
        }
            Client newClient = new Client();
            newClient.setFirstName(dto.getFirstName());
            newClient.setLastName(dto.getLastName());
            newClient.setEmail(dto.getEmail());
            clientRepository.save(newClient);
            dto.setId(newClient.getId());
            return dto;
    }
}
