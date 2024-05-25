package com.rnr.example.graphql.shop.main.services;

import com.rnr.example.graphql.shop.main.model.Order;
import com.rnr.example.graphql.shop.main.model.OrderStatus;
import com.rnr.example.graphql.shop.main.repository.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);
    private final OrderMapper orderMapper;

    public OrderService(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }
    
    public Order findById(int id) {
        return orderMapper.findById(id);
    }

    public Order createOrder(Order order) {
        return orderMapper.insertOrder(order);
    }

    public List<Order> findOrdersByClient(int id) {
        return orderMapper.findOrdersByClient(id);
    }
    
    public Order findOrderWithDetails(int id){
        return orderMapper.findOrderWithDetails(id);
    }

    public Order updateStatus(int id, OrderStatus orderStatus) {
        Map<String, Object> params = Map.of("id", id, 
            "orderStatus", orderStatus.name());
        int numUpd = orderMapper.updateStatus(params);
        if(numUpd == 0){
            LOGGER.info("Order {} was not updated", id);
        }
        return this.findById(id);
    }
}
