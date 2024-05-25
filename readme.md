# GraphQL app example

This is a project based on Spring Boot 3 with GraphQL. It relies on a H2 in-memory database. Tables and data are created through Sql scripts so that they will 
be loaded once it is run.

## Lessons learned

Make sure you have defined correctly the schema.graphqls. If you fail defining the file name (or extension, e.g .graphql instead .graphqls) you will not be able to access to the graphiql console.

## Query and mutation examples

StaffAll
```
query MyQuery {
  staffAll {
    id
    firstName
    lastName
    address {
      country{
        country
      }
      zipCode
    }
  }
}
```

Staff by Id
```
query MyQuery2 {
  staffId(id: 1) {
    id
    firstName
    lastName
    address {
      address
      city
      zipCode
      country {
        country
      }
    }
  }
}
```

Client By Id
```
query client {
  clientById(id: 4) {
    id
    firstName
    lastName
    address {
      address
      city
      zipCode
      country {
        country
      }
    }
  }
}
```

Country (insertion/mutation)
```
mutation country {
 #Note that the param name must match with what is defined in the schema. See commented piece below.
  newCountry(input:{
    country: "Mexico"
  }) {
    country
  }
}
```
```
extend type Mutation {
    newCountry(input: CountryInput!): Country
}
```

Note that, additionally, we must make sure that the argument name is properly set in the controller so that the method signature should be as below:
```
public Country insertCountry(@Argument(name="input") CountryInput country){
```
Another example of mutations for creating new Stuff.

```
mutation{
	newStaff(staff: {
    firstName:"Homer",
    lastName: "Simpson",
  	hireDate: "2024-04-24",
  	salary: 50000
  }) {
    firstName
    lastName
  }
}
```

```
query {
	orderById(id: 1){
    client {
      firstName
      lastName
    }
    orderDate
    billingAddress 
  }
}
```

Using defined enum type. Note that we are passing the value without quotes.
```
mutation {
	updateOrderStatus(id: 1, orderStatus: DELIVERED){
    id
    orderDate
    billingAddress
    orderStatus
  }   
}
```

Result of this last mutation can be similar as below:
```
{
  "data": {
    "updateOrderStatus": {
      "id": "2",
      "orderDate": "2024-02-01",
      "billingAddress": "St. One",
      "orderStatus": "DELIVERED"
    }
  }
}
```

```
query {
	ordersByClient(id: 2){
    orderDate
    billingAddress
	client {
        firstName
  	    lastName
    }
  }
}
```
The equivalent with a fragment

```
fragment cliFragment on Client{
    firstName
    lastName
}

query {
	ordersByClient(id: 2){
    orderDate
    billingAddress
    client{
    	... cliFragment
    }
  }
}
```
Inputs can also be used in Queries and not only on Mutations. For example, you may define a salary range as a 
criteria to find employees.
```
input SalaryInput {
    minimum: NonNegativeFloat
    maximum: NonNegativeFloat
}
```

```
{
	staffSalary(range: {
    minimum: 36000
    maximum: 50000
  }) {
    firstName,
    lastName,
  	hireDate,
  	salary
  }
}
```


### Fragments 
Here is an example of combining queries along with fragments. Note how concise the query is by using fragments. 

Considering that schema definition:
```
type Staff implements Person {
    id: ID!
    firstName: String!
    lastName: String!
    address: Address!
    hireDate: Date!
    salary: Int
}

type FormerStaff implements Person {
    id: ID!
    firstName: String!
    lastName: String!
    address: Address!
    hireDate: Date!
    terminationDate: Date!
    salary: Int
}

extend type Query{
    staffId(id: ID): Staff
    staff: [Staff]
    formerStaff: [FormerStaff]
}
```
We can get results with the following below: 
```
fragment personFragment on Person {
  id
  firstName
  lastName
  address {
    city
    zipCode
  }
}

query getStaffAndFormer{
  staff {
    ... personFragment
    hireDate
  }
  formerStaff{
  	... personFragment
    hireDate
    terminationDate
  }
}
```

This is really powerful since we are retrieving data from two methods in StaffController in a single request with 
just one endpoint (The one that uses Graphql). This makes the difference over the classical ReST architecture. 

### Only one fragment per type

````
fragment staffFragment on Staff {
  id
  firstName
  lastName
  address {
    city
    zipCode
  }
}

fragment staffFormerFragment on FormerStaff {
  id
  firstName
  lastName
  address {
    city
    zipCode
  }
}

query getStaffAndFormer{
	staff {
    ... staffFragment
  }
  formerStaff{
  	... staffFormerFragment
  }
}
````

#### Extending scalars

In many scenarios we might need to control that certain values are within a range or many other possible constraints.
 For example, when dealing with salaries for employees it makes no sense a negative value. So given a field 
 definition as shown below will let any floating point value:
 
```
type Staff {
  ...
  salary: float
}
```
Instead, we need to use a custom scalar. Fortunately, the graphql-java-extended-scalars includes, among other scalars, 
the NonNegativeFloat which is suitable for this case.

The most interesting point, is that if we store in DB an invalid value (e.g negative salary)

```
INSERT INTO staff(FIRST_NAME,LAST_NAME,BIRTH_DATE,HIRE_DATE,STAFF_ID,ADDRESS_ID,SALARY,DISABLED) 
    VALUES('John', 'Doe', '1988-10-18', '2022-05-21', 2, 6, -38500, true);
```

and we then perform a graphQL query like the following
```
query qStaff{
	staffId(id: 5){
    	firstName
    	lastName
      hireDate
    	salary
    	terminationDate
    	address {
        address
        zipCode
      }
  }
}
```
We get the following output that highlights the origin of the error.

```
{
  "errors": [
    {
      "message": "Can't serialize value (/staffId/salary) : The value must be greater than or equal to zero",
      "path": [
        "staffId",
        "salary"
      ],
      "extensions": {
        "classification": "DataFetchingException"
      }
    }
  ],
  "data": {
    "staffId": null
  }
}
```

Or you can try to add an employee with a negative salary.
```
mutation{
	newStaff(staff: {
    firstName:"Oliver",
    lastName: "Aton",
  	hireDate: "2024-01-09",
  	salary: -55500
  }) {
    firstName
    lastName
  }
}
```

Output

```
{
  "errors": [
    {
      "message": "Validation error (WrongType@[newStaff]) : argument 'staff.salary' with value 'IntValue{value=-55550}' is not a valid 'NonNegativeFloat' - The value must be greater than or equal to zero",
      "locations": [
        {
          "line": 3,
          "column": 11
        }
      ],
      "extensions": {
        "classification": "ValidationError"
      }
    }
  ]
}
```

### The GraphlQL Response

There can be 2 different sections in a GraphQL response: 

1. data: If the request was completely successful it will only contain this block showing the data result.
2. error: Can be shown due to many possible reasons.

Both, data and error, might appear if the request was partially ok. We can get an example by performing this query, 
considering we stored an employee with negative salary:

```
query qStaff{
  allStaff {
    firstName
    lastName
    salary
  }
}
```

```
{
  "errors": [
    {
      "message": "Can't serialize value (/allStaff[6]/salary) : The value must be greater than or equal to zero",
      "path": [
        "allStaff",
        6,
        "salary"
      ],
      "extensions": {
        "classification": "DataFetchingException"
      }
    }
  ],
  "data": {
    "allStaff": [
      {
        "firstName": "Rob",
        "lastName": "Schaffer",
        "salary": 60000
      },
      [...]
      }
  }
```

### Aliases

This is an example of how to use an alias. Let's say you want to shorten the description field name in the response. 
You only have to prepend with the alias and semicolon. It also works for Objects/Collection of objects

```
{
	customers {
    name,
    age,
    summary
    Ords: orders {
      desc: description
    }
  }
}
```
Output
```
  "data": {
    "customers": [
      {
        "name": "sam",
        "age": 80,
        "summary": "name:sam,age:20,city:atlanta",
        "Ords": [
          {
            "desc": "sam-product-1"
          },
          {
            "desc": "sam-product-2"
          }
        ]
      }
```
Aliases can also be worthy to execute the same query with different parameter values.
```
{
    c1 : customerById(id:1){
        [fields]
    }
    c2 : customerById(id:2){
        [fields]
    }
}
```

#### Playing with union

Now if we created union that is comprised Staff and FormerStaff, and it is used as an output for the queries
```
interface Person {
    id: ID!
    firstName: String!
    lastName: String!
    address: Address!
}
type Staff implements Person {
    id: ID!
    firstName: String!
    lastName: String!
    address: Address!
    hireDate: Date!
    salary: Int
}

type FormerStaff implements Person {
    id: ID!
    firstName: String!
    lastName: String!
    address: Address!
    hireDate: Date!
    terminationDate: Date!
    salary: Int
}
union CombinedStaff = Staff | FormerStaff

extend type Query{
    staffId(id: ID): Staff
    staff: [CombinedStaff]
    formerStaff: [CombinedStaff]
}
```

If we run this query

```
  query getStaffAndFormer{
	staff {
    ... personFragment
    salary
  }
  formerStaff{
  	... personFragment
    hireDate
    terminationDate
  }
}
```
This will raise a query error. If we correct the query by only using the person fragment then it will work. This is 
due to the fact that both types implement the Person interface. The main drawback is that we are not able to retrieve 
info such as salary,
 hire date, etc.
````
"errors": [
    {
      "message": "Validation error (FieldUndefined@[staff/salary]) : Field 'salary' in type 'CombinedStaff' is undefined",
      "locations": [
        {
          "line": 14,
          "column": 5
        }
      ],
      "extensions": {
        "classification": "ValidationError"
      }
    },
````
Then how could we solve this?

## GraphQL Clients

Aside from GraphiQL provided by default, you can choose Altair. You can install the Altair plugin for Google Chrome. 
Or you may also use Postman as you might have used before for testing ReST services.

#### Resources

Here is a list of links that helped me to build this project.

https://www.sivalabs.in/mybatis-tutorial-part-3-mapping-relationships/

https://hygraph.com/blog/combine-graphql-queries

https://techdozo.dev/spring-for-graphql-interfaces-and-unions/

https://github.com/techdozo/graphql-interface/tree/master

https://medium.com/@dhakresumitra5/graphql-input-types-and-field-arguments-90f648681cc5

https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties.web


