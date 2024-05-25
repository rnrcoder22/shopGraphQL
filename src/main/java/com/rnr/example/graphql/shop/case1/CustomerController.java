package com.rnr.example.graphql.shop.case1;

import com.rnr.example.graphql.shop.case1.dto.Customer;
import com.rnr.example.graphql.shop.case1.dto.CustomerOrderDto;
import com.rnr.example.graphql.shop.case1.service.CustomerService;
import com.rnr.example.graphql.shop.case1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService service;

    @Autowired
    private OrderService orderService;

   // @QueryMapping
    @SchemaMapping(typeName = "Query")
    public Flux<Customer> customers(){
        return this.service.allCustomers();
    }

    @SchemaMapping(typeName = "Customer")
    public Flux<CustomerOrderDto> orders(Customer customer, @Argument Integer limit){
        System.out.println("Orders method invoked for " + customer.getName());
        return this.orderService.ordersByCustomerName(customer.getName())
                .take(limit);
    }

}
