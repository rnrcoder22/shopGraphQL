package com.rnr.example.graphql.shop.main.model;

import com.rnr.example.graphql.shop.main.exceptions.ShopException;
import java.util.Arrays;

public enum OrderStatus {
    NON_DELIVERED,
    SHIPPING,
    DELIVERED;
    
    public static OrderStatus lookUp(final String value){
        return Arrays.stream(OrderStatus.values())
            .filter(os -> os.name().equalsIgnoreCase(value))
            .findFirst().orElseThrow(() -> new ShopException("Unexpected value : " + value));
    }
}
