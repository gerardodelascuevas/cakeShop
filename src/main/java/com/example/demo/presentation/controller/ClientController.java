package com.example.demo.presentation.controller;

import com.example.demo.application.dto.ClientDto;
import com.example.demo.application.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Gerardo De Las Cuevas
 */
@RestController
@RequestMapping(value = "/api/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping(value = "all")
    public ResponseEntity<List<ClientDto>> getAllClients(){
        return ResponseEntity.ok(service.getAllClientsAsDto());
    }

    @PostMapping
    public ResponseEntity<ClientDto> store(@RequestBody ClientDto clientDto){
        return ResponseEntity.ok(service.storeClient(clientDto));
    }
}
