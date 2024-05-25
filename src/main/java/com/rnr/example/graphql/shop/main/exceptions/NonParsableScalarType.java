package com.rnr.example.graphql.shop.main.exceptions;

public class NonParsableScalarType extends RuntimeException {
    
    public NonParsableScalarType(String msg){
        super(msg);
    }
    
    public NonParsableScalarType(String msg, Throwable throwable){
        super(msg, throwable);
    }
}
