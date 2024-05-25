--As a rule of thumb, to make H2 generates new and updated Ids when inserting rows, 
--do not specify ids in the data script or else ids will start with 1. Hence, it will
--lead to errors when creating new data due to PK violation.

--COUNTRIES 
INSERT INTO countries (country) VALUES ('USA');
INSERT INTO countries (country) VALUES ('Canada');
INSERT INTO countries (country) VALUES ('Spain');
INSERT INTO countries (country) VALUES ('France');
INSERT INTO countries (country) VALUES ('Italy');
INSERT INTO countries (country) VALUES ('United Kingdom');
INSERT INTO countries (country) VALUES ('Norway');
INSERT INTO countries (country) VALUES ('Finland');
INSERT INTO countries (country) VALUES ('Sweden');
INSERT INTO countries (country) VALUES ('Germany');
INSERT INTO countries (country) VALUES ('Brazil');
INSERT INTO countries (country) VALUES ('Portugal');

--ADDRESSES 
INSERT INTO address(ADDRESS, CITY, COUNTRY_ID, ZIP_CODE)
    VALUES('Rue Trocadero', 'Paris', 4, '23456');
INSERT INTO address(ADDRESS, CITY, COUNTRY_ID, ZIP_CODE)
    VALUES('Notting hill', 'London', 7, '15625');
INSERT INTO address(ADDRESS, CITY, COUNTRY_ID, ZIP_CODE)
    VALUES('Carlos III', 'Madrid', 3, '28080');
INSERT INTO address(ADDRESS, CITY, COUNTRY_ID, ZIP_CODE)
    VALUES('5th Avenue', 'New York', 1, '09683');
INSERT INTO address(ADDRESS, CITY, COUNTRY_ID, ZIP_CODE)
    VALUES('Rua Jose Joaquim ', 'Tavira', 12, '32499');
INSERT INTO address(ADDRESS, CITY, COUNTRY_ID, ZIP_CODE)
    VALUES('Wyatt Avenue', 'Toronto', 2, '23456');
INSERT INTO address(ADDRESS, CITY, COUNTRY_ID, ZIP_CODE)
    VALUES('San Jose St.', 'Sacramento', 1, '01987');
INSERT INTO address(ADDRESS, CITY, COUNTRY_ID, ZIP_CODE)
    VALUES('Blas de Lezo', 'Lerma', 3, '34002');
--STAFF    
INSERT INTO staff(FIRST_NAME,LAST_NAME,BIRTH_DATE,HIRE_DATE,STAFF_ID,ADDRESS_ID,SALARY,DISABLED) 
    VALUES('Rob', 'Schaffer', '1980-05-01', '2019-04-12', null, 4, 60000, false);
INSERT INTO staff(FIRST_NAME,LAST_NAME,BIRTH_DATE,HIRE_DATE,STAFF_ID,ADDRESS_ID,SALARY) 
    VALUES('Carlos', 'Gonzalez', '1990-06-01', '2021-11-30', 1, 3, 40000);
INSERT INTO staff(FIRST_NAME,LAST_NAME,BIRTH_DATE,HIRE_DATE,STAFF_ID,ADDRESS_ID,SALARY) 
    VALUES('Antoine', 'Namark', '1991-05-01', '2022-03-19', 2, 1, 37000);
INSERT INTO staff(FIRST_NAME,LAST_NAME,BIRTH_DATE,HIRE_DATE,STAFF_ID,ADDRESS_ID,SALARY) 
    VALUES('Suzanne', 'Martins', '1996-12-09', '2022-04-22', 2, 7, 35000);
INSERT INTO staff(FIRST_NAME,LAST_NAME,BIRTH_DATE,HIRE_DATE,STAFF_ID,ADDRESS_ID,SALARY,DISABLED) 
    VALUES('John', 'Doe', '1988-10-18', '2022-05-21', 2, 6, 38500, true);
INSERT INTO staff(FIRST_NAME,LAST_NAME,BIRTH_DATE,HIRE_DATE,STAFF_ID,ADDRESS_ID,SALARY) 
    VALUES('Joao', 'Penha', '1984-01-19', '2022-05-23', 5, 5, 29000);
INSERT INTO staff(FIRST_NAME,LAST_NAME,BIRTH_DATE,HIRE_DATE,STAFF_ID,ADDRESS_ID,SALARY, TERMINATION_DATE) 
    VALUES('Lucas', 'Jones', '1988-06-21', '2022-02-03', 5, 2, -29000, '2023-03-01');
    
--
----PRODUCTS 
INSERT INTO products (name, description, price, no_items, restock_date, available_regions) 
    VALUES ('Coolest Headphones', 'Wireless headphones', 60.95, 125, '2023-12-01', 'US,EN,FR,GE');
INSERT INTO products (name, description, price, no_items, restock_date, available_regions) 
    VALUES ('Rocky SSD', 'Fast and powerful storage. Up to 1TB', 599.99, 2, '2024-02-01', 'US,CA');
INSERT INTO products (name, description, price, no_items, restock_date, available_regions) 
    VALUES ('Toyland - Castle for kids', 'A castle that your kids will enjoy. It includes knights, siege weapons and a dragon', 
        106.99, 36, '2024-03-15', 'ES,IT,FR');
INSERT INTO products (name, description, price, no_items, restock_date, available_regions) 
    VALUES ('Mario Kart', 'A fun video-game', 45, 0, '2024-03-15', 'US,UK,JA');
--
----CLIENTS
INSERT INTO clients(FIRST_NAME,LAST_NAME,ADDRESS_ID,CREATE_DATE) 
    VALUES('Simon', 'Frasier', 8, '2022-04-12');
INSERT INTO clients(FIRST_NAME,LAST_NAME,ADDRESS_ID,CREATE_DATE) 
    VALUES('David', 'Vega', 4, '2023-07-14');
INSERT INTO clients(FIRST_NAME,LAST_NAME,ADDRESS_ID,CREATE_DATE) 
    VALUES('Jeremy', 'Hartwood', 3, '2023-06-08');
INSERT INTO clients(FIRST_NAME,LAST_NAME,ADDRESS_ID,CREATE_DATE) 
    VALUES('Carmen', 'Romero', 6, '2024-01-08');  

----ORDERS   
INSERT INTO orders (CLIENT_ID,ORDER_DATE,ORDER_STATUS,BILLING_ADDRESS)
    VALUES( 4, '2024-02-01', 'NON_DELIVERED', 'St. One');
INSERT INTO orders (CLIENT_ID,ORDER_DATE,ORDER_STATUS,BILLING_ADDRESS) 
    VALUES(3, '2023-06-09', 'SHIPPING', 'St. Two');
INSERT INTO orders (CLIENT_ID,ORDER_DATE,ORDER_STATUS,BILLING_ADDRESS) 
    VALUES(2, '2023-06-09', 'DELIVERED', 'St. Three');

----ORDER_DETAILS
INSERT INTO ordersDetails (ORDER_ID,PRODUCT_ID,QUANTITY)
    VALUES(1, 1, 1);
INSERT INTO ordersDetails (ORDER_ID,PRODUCT_ID,QUANTITY)
    VALUES(2, 2, 15);
INSERT INTO ordersDetails (ORDER_ID,PRODUCT_ID,QUANTITY)
    VALUES(2, 1, 14);
INSERT INTO ordersDetails (ORDER_ID,PRODUCT_ID,QUANTITY)
    VALUES(3, 4, 1);



    
