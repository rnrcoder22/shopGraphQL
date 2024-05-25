package com.rnr.example.graphql.shop.main.services;

import com.rnr.example.graphql.shop.main.model.Address;
import com.rnr.example.graphql.shop.main.repository.AddressMapper;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final AddressMapper addressRepository;
    
    public AddressService(AddressMapper addressRepository) {
        this.addressRepository = addressRepository;
    }
    
    public Address findById(int id) {
        return addressRepository.findById(id);
    }
}
