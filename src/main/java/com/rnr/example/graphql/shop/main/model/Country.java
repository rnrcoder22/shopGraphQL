package com.rnr.example.graphql.shop.main.model;

import org.apache.ibatis.type.Alias;
import java.util.Objects;

@Alias("country")
public class Country {

    private Integer id;
    private String country;

    public Country() {
        
    }
    
    public Country(String country) {
        this.country = country;
    }

    public Country(Integer id, String name) {
        this.id = id;
        this.country = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Country country = (Country) o;
        return Objects.equals(id, country.id) && Objects.equals(this.country, country.country);
    }

    @Override public int hashCode() {
        return Objects.hash(id, country);
    }

    @Override 
    public String toString() {
        return "Country{" + "id=" + id + ", name='" + country + '\'' + '}';
    }
}
