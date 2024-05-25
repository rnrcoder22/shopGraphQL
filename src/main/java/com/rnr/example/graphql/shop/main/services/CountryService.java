package com.rnr.example.graphql.shop.main.services;

import com.rnr.example.graphql.shop.main.model.Country;
import com.rnr.example.graphql.shop.main.model.input.CountryInput;
import com.rnr.example.graphql.shop.main.repository.CountryMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CountryService {
    
    private final CountryMapper countryRepository;
    
    public CountryService(CountryMapper countryRepository){
        this.countryRepository = countryRepository;
    }
    
    public Country findById(int id){
        return countryRepository.findById(id);
    }

    public List<Country> findAll() {
        return countryRepository.findAll();
    }
    
    public Country insert(CountryInput country) {
        countryRepository.insert(country);
        return new Country(country.country());
    }
    
    public Country insert(String countryName) {
        countryRepository.insertWithName(countryName);
        return new Country(countryName);
    }
}
