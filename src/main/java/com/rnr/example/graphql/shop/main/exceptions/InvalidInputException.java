package com.rnr.example.graphql.shop.main.exceptions;

public class InvalidInputException extends RuntimeException{

    public InvalidInputException(String msg){
        super(msg);
    }
    
    public InvalidInputException(String msg, Throwable throwable){
        super(msg, throwable);
    }
}
