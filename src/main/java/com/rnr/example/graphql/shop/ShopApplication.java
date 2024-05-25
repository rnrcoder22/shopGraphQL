package com.rnr.example.graphql.shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.rnr.example.graphql.shop.${app}")
public class ShopApplication implements CommandLineRunner {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}
	
	@Override
    public void run(String... args) {
        LOGGER.info("Running shop application demo");
    }

}
