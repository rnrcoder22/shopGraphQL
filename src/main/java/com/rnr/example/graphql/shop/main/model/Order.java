package com.rnr.example.graphql.shop.main.model;

import java.sql.Date;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Order {
    
    private int id;
    private Client client;
    private Date orderDate;
    private OrderStatus orderStatus;
    private String billingAddress;
    private List<OrderDetails> orderDetails;

 }
