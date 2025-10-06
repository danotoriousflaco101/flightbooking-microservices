<div align="center">
<h1><strong>Flight Booking Microservices System ✈️</strong></h1>
<img src="https://miro.medium.com/1*m1NPxw31RoQRG8KDM0xQhA.png" alt="ecommerce" width="500"/>
</div>

<div align="center">
<br/>
<strong>A microservices-based flight booking system built with Java, Spring Boot, and Spring Cloud.
</strong>
<br/>
<br/>
This project demonstrates backend concepts like Service Discovery (Eureka), API Gateway,<br/> inter-service communication, and containerized databases with Docker.<br/>
(and follows the three c's: Containerize, Communication, Coordination.)  
</div>
<br/>


📡 Features
----------

<strong>Microservices Architecture:</strong> The system is broken down into independent services (user-service, flight-search-service, booking-service) to ensure maintainability and scalability.
<br/>

<strong>Service Discovery:</strong> Using Eureka server to allow services to register and discover each other on the network.
<br/>

<strong>Centralized API Gateway:</strong> Single entry point (api-gateway) handles all external requests, authenticates them, and routes them to the correct microservice.
<br/>

<strong>JWT Security:</strong> The user-service manages a full authentication and authorization flow based on JSON Web Tokens.
<br/>

<strong>Inter-Service Communication:</strong> The booking-service communicates synchronously with the flight-search-service using WebClient to check for flight availability in real-time.
<br/>

<strong>Containerized Database:</strong> Each service has its own dedicated PostgreSQL database, managed by Docker for a clean and reproducible development environment and to avoid the classic "it works on my computer!".
<br/>


⚠️ Prerequisites
------------


Java Development Kit (JDK 21 or newer).

Apache Maven.

Docker Desktop installed and running.

An API client like Insomnia or Postman for testing the endpoints.
<br/>

🛠️ Tech Stack
-----

<strong>Java 21:</strong> The core programming language.

<strong>Spring Boot:</strong> The framework used to build each microservice.

<strong>Spring Cloud:</strong> Used for Eureka Server, Eureka Client, and API Gateway.

<strong>Spring Security:</strong> Handles authentication and role-based authorization with JWT.

<strong>Spring Data JPA:</strong> Manages database interaction.

<strong>PostgreSQL:</strong> The relational database for data persistence.

<strong>Docker:</strong> Used for containerizing and running the database.

<strong>jjwt:</strong> A library for creating and validating JSON Web Tokens.

<strong>MapStruct:</strong> Generates mappers between DTOs and Entities.

<strong>Lombok:</strong> To reduce boilerplate code.

<strong>Maven:</strong> The project build and dependency management tool.
</br>


💻 Tools & Technologies
------------

<br/>
<p align="center">
<a href="#"><img src="https://img.shields.io/badge/macOS-000000?logo=apple&logoColor=F0F0F0" alt="macOS"></a>
<a href="#"><img src="https://img.shields.io/badge/Spotify-1ED760?logo=spotify&logoColor=white" alt="Spotify"></a> 
<a href="#"><img src="https://custom-icon-badges.demolab.com/badge/Visual%20Studio%20Code-0078d7.svg?logo=vsc&logoColor=white" alt="VSC"></a>
<a href="#"><img src="https://img.shields.io/badge/Insomnia-5849be?logo=insomnia&logoColor=white" alt="Insomnia"></a>
<a href="#"><img src="https://img.shields.io/badge/Spring-6DB33F?logo=spring&logoColor=fff&style=flat" alt="Spring"></a>
<a href="#"><img src="https://img.shields.io/badge/Apache%20Maven-C71A36?logo=apachemaven&logoColor=white" alt="Maven"></a>
<a href="#"><img src="https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=fff&style=flat" alt="Docker"></a>  
<a href="#"><img src="https://img.shields.io/badge/PostgreSQL-4169E1?logo=postgresql&logoColor=fff&style=flat" alt="Postgresql"></a>  
<a href="#"><img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot&logoColor=fff&style=flat" alt="Springboot"></a>    
<a href="#"><img src="https://img.shields.io/badge/Git-F05032?logo=git&logoColor=white" alt="Git"></a>
<a href="#"><img src="https://img.shields.io/badge/GitHub-181717?logo=github&logoColor=white" alt="GitHub"></a> 


</p>
<br/>

▶️ Setup & Running the Application
-----

1) START THE DATABASES
<br/>

Open a terminal and run the commands to create and start the three required databases. Ensure the password (your_password) matches the one in your application.properties files.

<strong>docker run --name user-db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=your_password -e POSTGRES_DB=user_service_db -p 5432:5432 -d postgres</br>
docker run --name flight-db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=your_password -e POSTGRES_DB=flight_service_db -p 5433:5432 -d postgres</br>
docker run --name booking-db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=your_password -e POSTGRES_DB=booking_service_db -p 5434:5432 -d postgres</strong>
<br/>
<br/>

2) RUN THE MICROSERVICES <strong>(Order is critical!)</strong>
<br/>
Open five separate terminals. In each one, navigate to the respective project folder and run it using <strong>mvn spring-boot:run</strong>.<br/>
Follow this startup order:


  I. service-discovery

  II. user-service

  III. flight-search-service

  IV. booking-service

  V. api-gateway
<br/>
<br/>


3) VERIFY THE NETWORK

Open a browser and navigate to <strong>http://localhost:8761</strong>. After a moment, you should see all the services registered on the Eureka dashboard.
<br/>
<br/>


🧑‍💻 API Endpoints & Testing Flow
--------

Use an API client like Insomnia. All requests must be made to the API Gateway on port 8080.
</br>
</br>


1) Setup (as ADMIN)

 
-> <strong>Register an Admin:</strong> POST http://localhost:8080/api/auth/signup

Body: {"username": "admin", "email": "admin@email.com", "password": "password123", "role": ["admin"]}

-> <strong>Log in as Admin:</strong> POST http://localhost:8080/api/auth/signin

Body: {"username": "admin", "password": "password123"}

Action required: Copy the token from the response.

-> <strong>Create a Flight:</strong> POST http://localhost:8080/api/flights

Auth: Bearer Token (use the ADMIN token).

Body: {"flightNumber": "MC420", "origin": "MXP", "destination": "LAX", "departureTime": "2025-12-20T10:00:00", "arrivalTime": "2025-12-20T13:00:00", "availableSeats": 5}
</br>
</br>

2) Booking (as USER)


-> <strong>Register a User:</strong> POST http://localhost:8080/api/flights

Body: {"username": "marco", "email": "marco@email.com", "password": "password123"}

-> <strong>Log in as User:</strong> POST http://localhost:8080/api/auth/signin

Action: Copy the token and the id for the user "marco".

-> <strong>Create the booking:</strong> POST http://localhost:8080/api/bookings

Auth: Bearer Token (use the USER token).

Header: Add a header "X-User-Id" with the value of USER ID (e.g., X-User-Id: 2).

Body: {"flightId": 1, "seatNumber": "14A"}

Expected Response: 201 Created with the booking details.


