# Machine Maintenance Scheduler

A microservices-based backend system for managing machines and scheduling maintenance tasks using Spring Boot and Spring Cloud.

---

# Architecture Overview

This project follows a Microservices Architecture using:

- Service Registry (Eureka)
- API Gateway
- Independent Business Services
- REST Communication
- Centralized Service Discovery

---

#  Services Overview

| Service | Description | Port |
|----------|-------------|------|
| Service Registry | Registers and discovers all microservices | 8761 |
| API Gateway | Entry point for all client requests | 8080 |
| Machine Service | Manages machine data (CRUD operations) | 8081 |
| Maintenance Service | Manages maintenance scheduling and tracking | 8082 |

---

# Service URLs

Service Registry  
http://localhost:8761  

API Gateway  
http://localhost:8080  

Machine Service (Direct Access)  
http://localhost:8081  

Maintenance Service (Direct Access)  
http://localhost:8082  

---

# How to Run the Project

Start each service in separate terminals.

## Start Microservices

cd (Path of the Microservice)
mvn spring-boot:run

## Swagger API Usage

Swagger (OpenAPI) is integrated into the Machine Service and Maintenance Service for API documentation and testing.

Access Swagger UI
Machine Service

http://localhost:8081/swagger-ui/index.html

Maintenance Service

http://localhost:8082/swagger-ui/index.html
