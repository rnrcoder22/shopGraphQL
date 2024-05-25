package com.rnr.example.graphql.shop.main.controllers;

import com.rnr.example.graphql.shop.main.model.Order;
import com.rnr.example.graphql.shop.main.model.OrderStatus;
import com.rnr.example.graphql.shop.main.services.OrderService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class OrderController {
    
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    
    @QueryMapping(name = "orderById")
    public Order findById(@Argument int id) {
        return orderService.findById(id);
    }
    @MutationMapping(name = "newOrder")
    public Order createOrder(@Argument Order order){
        return orderService.createOrder(order);
    }
    
    //The equivalent for this annotation @QueryMapping(name = "ordersByClient"),
    //is the following
    @SchemaMapping(
        typeName = "Query", field="ordersByClient"
    )
    public List<Order> findOrdersByClient(@Argument int id) {
        return orderService.findOrdersByClient(id);
    }
    
    @SchemaMapping(
        typeName = "Query", field="orderDetails"
    )
    public Order findOrdersDetailsByOrder(@Argument int id) {
        return orderService.findOrderWithDetails(id);
    }
    
    @MutationMapping
    public Order updateOrderStatus(@Argument int id, @Argument OrderStatus orderStatus) {
        return orderService.updateStatus(id, orderStatus);
    }
}
