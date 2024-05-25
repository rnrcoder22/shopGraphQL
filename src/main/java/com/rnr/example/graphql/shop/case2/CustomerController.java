package com.rnr.example.graphql.shop.case2;

import com.rnr.example.graphql.shop.case2.dto.Customer;
import com.rnr.example.graphql.shop.case2.dto.CustomerOrderDto;
import com.rnr.example.graphql.shop.case2.service.CustomerService;
import com.rnr.example.graphql.shop.case2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.stream.Collectors;

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

    /*
    * Given this type
    * type Customer{
    *         id: ID!
    *         name: String
    *         age: Int
    *         city: String
    *         orders: [CustomerOrder]!
    *  }
    * 
    * To avoid the N+1 problem, we have to:
    * 1. we replace the Customer parameter by a List<Customer>
    * 2. Remove SchemaMapping and set BatchMapping instead
    * 
    * This way we are passing a batch of customers so that GraphQL will know 
    * how to retrieve their orders at once.
    * 
    * So given this query.
    * {
    *    customers {
    *    name,
    *    age,
    *    orders {
    *      description
    *    }
    *  }
    *}
    * 
    * the field orders from Customer type will be mapped to CustomerController#orders
    */
    @BatchMapping(typeName = "Customer")
    public Flux<List<CustomerOrderDto>> orders(List<Customer> customerList) {
        System.out.println("Orders method invoked for " + customerList);
        return this.orderService.ordersByCustomerNames(
            customerList.stream()
                    .map(Customer::getName).collect(Collectors.toList()));
    }

    /*
     * Example of how to redefine a resolver at field scope inside Customer type.
     * To summarize, when a query is executed GraphQL will examine for each property, if
     * there's a function which is named as the corresponding property, 
     * as can be seen in this example. If there's no such function 
     * it will return the original value,
     * 
     * In this case, as a result, all clients will have age + N.
     * 
     * As a side note, (typeName="Customer") is optional as long as 
     * the parameter is defined and is Customer typed.
     */
    @SchemaMapping(typeName="Customer")
    public Mono<Integer> age(Customer customer){
        return Mono.just(customer.getAge() + 60);
    }
    
    /*
     * Defining the resolver for summary.
     */
    @SchemaMapping(typeName="Customer")
    public Mono<String> summary(Customer customer){
        return Mono.just(String.format("name:%s,age:%s,city:%s", 
            customer.getName(), customer.getAge(), customer.getCity()));
    }

}
