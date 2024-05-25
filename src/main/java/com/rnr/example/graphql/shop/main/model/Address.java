package com.rnr.example.graphql.shop.main.model;

import java.util.Objects;


public class Address {
 
    private int id;
    private String address;
    
    private String city;
    
    private Country country;
    
    private int zipCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Address address = (Address) o;
        return getId() == address.getId() && getZipCode() == address.getZipCode() && Objects.equals(getAddress(),
                address.getAddress()) && Objects.equals(getCity(), address.getCity()) && Objects.equals(getCountry(),
                address.getCountry());
    }

    @Override public int hashCode() {
        return Objects.hash(getId(), getAddress(), getCity(), getCountry(), getZipCode());
    }
}
