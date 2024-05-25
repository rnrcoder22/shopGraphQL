package com.rnr.example.graphql.shop.main.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Product {
    private int id;
    private String name;
    
    private String description;
    
    private BigDecimal price;
    
    private int noItems;
    
    private Date restockDate;
    
    private String availableRegions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getNoItems() {
        return noItems;
    }

    public void setNoItems(int noItems) {
        this.noItems = noItems;
    }

    public Date getRestockDate() {
        return restockDate;
    }

    public void setRestockDate(Date restockDate) {
        this.restockDate = restockDate;
    }

    public String getAvailableRegions() {
        return availableRegions;
    }

    public void setAvailableRegions(String availableRegions) {
        this.availableRegions = availableRegions;
    }
}
