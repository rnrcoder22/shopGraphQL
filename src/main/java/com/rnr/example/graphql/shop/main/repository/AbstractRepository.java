package com.rnr.example.graphql.shop.main.repository;

public interface AbstractRepository<T> {
    
    T findById(int id);
    
}
