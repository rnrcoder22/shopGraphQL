package com.rnr.example.graphql.shop.main.services;

import com.rnr.example.graphql.shop.main.model.Client;
import com.rnr.example.graphql.shop.main.repository.ClientMapper;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    
    private final ClientMapper clientRepository;

    public ClientService(ClientMapper clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client findById(int id) {
        return clientRepository.findById(id);
    }
}
