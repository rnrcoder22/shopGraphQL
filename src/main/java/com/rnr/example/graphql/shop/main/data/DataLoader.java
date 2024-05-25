package com.rnr.example.graphql.shop.main.data;

import com.rnr.example.graphql.shop.main.repository.CountryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Autowired CountryMapper repository;
	
	public DataLoader(){}
	
	public void loadData() {
//        LOGGER.info("Country id 10001 -> {}", repository.findById(1));
//        LOGGER.info("Inserting -> {}", repository.insert(new Country(7, "Spain")));
//        LOGGER.info("Update 10003 -> {}", repository.update(new Country(2, "Portugal")));
    }
}
