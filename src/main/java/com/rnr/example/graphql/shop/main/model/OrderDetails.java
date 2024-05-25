package com.rnr.example.graphql.shop.main.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class OrderDetails {
    
    private long id;
    private Product product;
    private int quantity;
    
}
