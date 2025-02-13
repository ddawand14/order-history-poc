# inmar-poc

Overview:

This is a Spring Boot application that provides an API to fetch order history. It demonstrates authentication and authorization using JWT and includes role-based access control.

Technology Stack: 
Java 17,
Spring Boot 3.2.1,
Spring Security 6,
H2 Database and
JWT Authentication 



Database Configuration (H2):

The application uses an H2 in-memory database. To connect and access the H2 database console:

Start the application. 


Open a web browser and navigate to: http://localhost:8080/h2-console


Use the following credentials to connect:


JDBC URL=   jdbc:h2:mem:testdb


Username=  sa


Password: (leave blank)


Click "Connect" to access the database.


Preloaded Data:

The application automatically inserts dummy data into the product, item, and orders tables using the data.sql script.
This helps in testing the Order History API without manual data entry.
User Roles & Authorization:

The application has two roles:


ADMIN – Has access to all APIs.


USER – Has restricted access.


The Order History API (/api/orders/history) is accessible only to ADMIN users to demonstrate proper authorization.


How to Use the Application:



1. Sign Up
To create a user, call the Sign Up API:

Endpoint:


POST /auth/signup


Request Body:


{
  "email": "admin@gmail.com",
  "name":"Rajesh",
  "password": "admin123"
}


If you use "admin@gmail.com", the role will be ADMIN.
Any other email will be assigned the USER role.


2. Login

Once signed up, log in to obtain a JWT token:


Endpoint:



POST /auth/login



Request Body:




{
  "email": "admin@gmail.com",
  "password": "admin123"
}


Response:

{
  "accessToken": "your-jwt-token",
  "expiresIn":"expires-in-ms"
}

Copy this JWT token for accessing protected APIs.



3. Access Order History (ADMIN Only)


Endpoint:



GET /api/orders/history


Headers:



{
  "Authorization": "Bearer your-jwt-token"
}


sample Response:


[

  {
  
    "id": 101,
    
    "orderDate": "2023-06-30T04:30:00.000+00:00",
    
    "totalAmount": 700,
    
    "items": [
    
      {
      
        "productId": "Product101",
        
        "productName": "Product A",
        
        "price":300,
        
        "quantity": 1
        
      },
      
      {
      
        "productId": "Product102",
        
        "productName": "Product B",
        
        "price":300,
        
        "quantity": 2
      }
    ]
    
    "status": "COMPLETED"
  }]


If the user is an ADMIN, the API will return order history. Otherwise, access will be denied.
