package com.rnr.example.graphql.shop.main.repository;

import com.rnr.example.graphql.shop.main.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper extends AbstractRepository<Order>{
    
    
    @Override
    Order findById(int id);
    
    @Select("SELECT * FROM order") 
    List<Order> findAll();

    Order insertOrder(Order order);
    
    List<Order> findOrdersByClient(int id);
    
    Order findOrderWithDetails(int id);

    int updateStatus(Map<String, Object> params);
}
