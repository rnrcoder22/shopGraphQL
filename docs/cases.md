## Case 01

Given this type
```
type Customer{
    id: ID!
    name: String
    age: Int
    city: String
    orders(limit: Int!): [CustomerOrder]!
}
```
We define the resolver for orders inside Customer type in the CustomerController
```
@SchemaMapping(typeName = "Customer")
public Flux<CustomerOrderDto> orders(Customer customer, @Argument Integer limit){
    System.out.println("Orders method invoked for " + customer.getName());
    return this.orderService.ordersByCustomerName(customer.getName())
            .take(limit);
}
```

Query example
```
{
	customers {
    name,
    age,
    orders(limit:3) {
      description
    }
  }
}
```

##Case 2

### N + 1 Problem

Witch Schema mapping we could see that the orders from CustomerController is invoked N times, one query for each order 

````
Orders method invoked for jake
Orders method invoked for mike
Orders method invoked for john
````

BatchMapping is the way to solve the N+1 problem along with receiving a list of customers (instead gathering a single Customer)
```
Orders method invoked for [Customer(id=1, name=sam, age=20, city=atlanta), Customer(id=2, name=jake, age=10, city=las vegas), Customer(id=3, name=mike, age=15, city=miami), Customer(id=4, name=john, age=5, city=houston)]
```
