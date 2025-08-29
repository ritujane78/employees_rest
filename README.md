# Employees

Versions
Java: 21
Spring Boot: 3.5.5
Springdoc: 2.7.0 

This project demonstrates the Authentication, Authorization and security configs of users for Rest APIs.

3 users are created firstly hardcoded, then from db . They are then authorised to access some endpoints based on their roles.
The database used to store Employees, Users and their roles is H2 database.

The Swagger UI is made to authorise users to handle CRUD operations on employees.

Eg. a user with a role of "EMPLOYEE" can only Read Employees. A user with role "MANAGER" can Post and Put. Likewise, "ADMIN" role users can Delete employees as well.
the user passwords are encrypted and stored in database. bycrypt algorithm is used.