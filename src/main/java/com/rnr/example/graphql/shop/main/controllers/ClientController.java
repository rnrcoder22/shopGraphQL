package com.rnr.example.graphql.shop.main.controllers;

import com.rnr.example.graphql.shop.main.model.Client;
import com.rnr.example.graphql.shop.main.services.ClientService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ClientController {
    
    private final ClientService clientService;
    
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    
    //If the method name from the controller differs from the query name in graphql it is required to set this last 
    //one in the annotation. Otherwise, if name matches, it will be automatically mapped.
    @QueryMapping(name="clientById")
    public Client findClientById(@Argument Integer id){
        return clientService.findById(id);
    }
    
}
