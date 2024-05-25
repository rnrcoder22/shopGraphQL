package com.rnr.example.graphql.shop.main.exceptions;

public class ShopException extends RuntimeException{
    
    public ShopException (String msg) {
        super(msg);
    }
    public ShopException (String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
