# 🛒 E-commerce Microservices Backend Ecosystem (Spring Boot)

[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://openjdk.java.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0+-green.svg)](https://spring.io/projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2023+-blue.svg)](https://spring.io/projects/spring-cloud)
[![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue.svg)](https://www.mysql.com/)
[![Gradle](https://img.shields.io/badge/Gradle-8.0+-blue.svg)](https://gradle.org/)
[![Eureka](https://img.shields.io/badge/Eureka-Discovery-yellowgreen.svg)](https://cloud.spring.io/spring-cloud-netflix/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

A **production-ready, scalable e-commerce backend ecosystem** built using **Spring Boot microservices**. The system is organized across three main services:

- **EcommerceApp_spring_boot**: Main e-commerce service for product, category, and inventory management.
- **OrderService-Ecommerce-Spring-boot**: Order management microservice.
- **Eureka-Ecommerce-SPring-boto**: Service discovery using Netflix Eureka.

---

## 🏗️ Microservices Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                       Eureka Server                         │
│                (Service Discovery Registry)                 │
└───────────────▲────────────────────────────▲────────────────┘
                │                            │
     ┌──────────┴──────────┐      ┌──────────┴──────────┐
     │   E-commerce App    │      │    Order Service    │
     │ (Product, Category, │      │ (Order Management)  │
     │   Inventory, API)   │      │                    │
     └──────────▲──────────┘      └──────────▲──────────┘
                │                            │
         ┌──────┴───────┐             ┌──────┴───────┐
         │   MySQL DB   │             │   MySQL DB   │
         └──────────────┘             └──────────────┘
```

---

## 🌟 Features

### Main E-commerce Service (`EcommerceApp_spring_boot`)
- **Product Management:** CRUD for products, brands, and inventory
- **Category System:** Hierarchical product categories
- **Full-text Search:** Advanced product search (MySQL FULLTEXT)
- **External API Integration:** FakeStore API via Retrofit
- **RESTful API:** Robust, clean endpoints

### Order Service (`OrderService-Ecommerce-Spring-boot`)
- **Order Processing:** Place, update, track, and manage orders
- **Order-Product Integration:** Connects with main e-commerce service for product info
- **Payment Hook Ready:** Easily extendable for payment gateway integration

### Eureka Discovery Server (`Eureka-Ecommerce-SPring-boto`)
- **Service Registry:** Registers and monitors all microservices
- **Health Monitoring:** Built-in service health checks
- **Dynamic Discovery:** Enables client-side service lookup and load balancing

---

## 🛠️ Technology Stack

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Cloud / Netflix Eureka**
- **Spring Data JPA (Hibernate)**
- **MySQL 8.0+**
- **Gradle**
- **Retrofit2 (External APIs)**
- **Lombok**

---

## 📁 Repository Structure

```
/
├── EcommerceApp_spring_boot/           # Main e-commerce service
├── OrderService-Ecommerce-Spring-boot/ # Order microservice
├── Eureka-Ecommerce-SPring-boto/       # Eureka Discovery Server
```

---

## 🔌 API Endpoints (Examples)

### Product Service (`EcommerceApp_spring_boot`)
- `GET    /products`                          — Get all products (external API)
- `GET    /products/{id}`                     — Get specific product
- `POST   /products`                          — Create new product (external API)
- `POST   /products/db/create`                — Create product in database
- `GET    /products/db`                       — Get all products from database
- `GET    /products/db/product/productname/{name}` — Full-text product search

### Category Service
- `GET    /categories`                        — List categories
- `POST   /categories`                        — Create category
- `GET    /categories/{id}/products`          — List products in a category

### Order Service (`OrderService-Ecommerce-Spring-boot`)
- `POST   /orders`                            — Place a new order
- `GET    /orders/{id}`                       — Get order details
- `GET    /orders/user/{userId}`              — Get all orders for a user

---

## 🚀 Quick Start

### Prerequisites
- Java 17+
- Gradle 8.0+
- MySQL 8.0+
- (Recommended) Separate terminal for each microservice

### Setup Steps

1. **Clone All Repositories**
    ```sh
    git clone https://github.com/DHRUVILPATEL2303/EcommerceApp_spring_boot.git
    git clone https://github.com/DHRUVILPATEL2303/OrderService-Ecommerce-Spring-boot.git
    git clone https://github.com/DHRUVILPATEL2303/Eureka-Ecommerce-SPring-boto.git
    ```

2. **Start Eureka Server**
    ```sh
    cd Eureka-Ecommerce-SPring-boto
    ./gradlew bootRun
    ```

3. **Start Databases**
    - Create required databases in MySQL for both main app and order service.

4. **Configure `.env` or `application.yml` for each microservice**
    - Set database credentials, Eureka URL, and service names as needed.

5. **Start Services**
    ```sh
    # In separate terminals
    cd EcommerceApp_spring_boot
    ./gradlew bootRun

    cd ../OrderService-Ecommerce-Spring-boot
    ./gradlew bootRun
    ```

---

## ⚙️ Example `application.yml` (E-commerce service)

```yaml
server:
  port: 8080

spring:
  application:
    name: ecommerce-service
  datasource:
    url: jdbc:mysql://localhost:3306/ecommerce_db
    username: your_username
    password: your_password
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
  instance:
    prefer-ip-address: true
```

---

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

> **For updates and details, visit the original repositories:**
> - [EcommerceApp_spring_boot](https://github.com/DHRUVILPATEL2303/EcommerceApp_spring_boot)
> - [OrderService-Ecommerce-Spring-boot](https://github.com/DHRUVILPATEL2303/OrderService-Ecommerce-Spring-boot)
> - [Eureka-Ecommerce-SPring-boto](https://github.com/DHRUVILPATEL2303/Eureka-Ecommerce-SPring-boto)