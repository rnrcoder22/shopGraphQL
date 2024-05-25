package com.rnr.example.graphql.shop.main.controllers;

import com.rnr.example.graphql.shop.main.exceptions.InvalidInputException;
import com.rnr.example.graphql.shop.main.model.Country;
import com.rnr.example.graphql.shop.main.model.input.CountryInput;
import com.rnr.example.graphql.shop.main.services.CountryService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;
import io.micrometer.common.util.StringUtils;

@Controller
public class CountryController {
    
    private CountryService countryService;
    
    public CountryController(CountryService countryService){
        this.countryService = countryService;
    }
    
    @QueryMapping
    public List<Country> findCountryAll(){
        return countryService.findAll();
    }
    
    @QueryMapping
    public Country findCountryById(@Argument int id){
        return countryService.findById(id);
    }

    /**
     * An example of mutation by passing an input typed object
     * @param country country input instance
     * @return
     */
    @MutationMapping("newCountry")
    public Country insertCountry(@Argument(name="input") CountryInput country){
        return countryService.insert(country);
    }

    /**
     * An example of mutation by passing simple parameters. It controls that name is not blank.
     * Can throw an error if a country with the given name already exists in DB.
     * @param name name of the country
     * @return
     */
    @MutationMapping("newCountryWithName")
    public Country insertCountry(@Argument(name="name") String name){
        if(StringUtils.isBlank(name)){
            throw new InvalidInputException("Country name cannot be null");
        }
        return countryService.insert(name);
    }
}
