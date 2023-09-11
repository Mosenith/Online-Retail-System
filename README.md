# Online Retail System

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Documentation](#documentation)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The "OnlineRetailSystem" project is a Java-based web application that serves as an online retail system. It utilizes the Spring Boot framework for building a robust and scalable system. This project aims to provide a seamless online shopping experience for customers while offering a secure and efficient back-end for managing products, orders, and user accounts.

## Features

Below are the list of features and its description of each feature.

1. **Spring Boot Framework**
   - **Description**: Utilizes the Spring Boot framework for building a robust and scalable online retail system.
   - **Dependency**: `org.springframework.boot`, `spring-boot-starter-actuator`, `spring-boot-starter-data-jpa`, `spring-boot-starter-web`

2. **Spring Security Integration**
   - **Description**: Integrates Spring Security for authentication and authorization in the online retail system.
   - **Dependency**: `spring-boot-starter-security`

3. **Data Access with JPA**
   - **Description**: Implements data access and persistence using Java Persistence API (JPA) for managing the database.
   - **Dependency**: `spring-boot-starter-data-jpa`

4. **RESTful Web Services**
   - **Description**: Develops RESTful web services to support interactions between the front-end and back-end of the online retail system.
   - **Dependency**: `spring-boot-starter-web`

5. **Model Mapping with ModelMapper**
   - **Description**: Uses the ModelMapper library to simplify and automate the mapping between DTOs and entities.
   - **Dependency**: `org.modelmapper`

6. **Database Integration**
   - **Description**: Integrates database connectivity, including MySQL and H2 databases for development and testing.
   - **Dependencies**: `com.mysql`, `com.h2database`

7. **JSON Serialization with Jackson**
   - **Description**: Utilizes Jackson libraries to handle JSON serialization and deserialization of objects.
   - **Dependencies**: `com.fasterxml.jackson.core`, `com.fasterxml.jackson.core`

8. **JWT Authentication**
   - **Description**: Implements JSON Web Token (JWT) authentication for securing endpoints and user sessions.
   - **Dependencies**: `io.jsonwebtoken`

9. **Testing with JUnit and RestAssured**
   - **Description**: Conducts unit and integration testing using JUnit and RestAssured for ensuring code quality and reliability.
   - **Dependencies**: `junit`, `io.rest-assured`

10. **Development and Testing Utilities**
    - **Description**: Includes development and testing tools like Spring Boot DevTools and HSQLDB for enhancing the development experience.
    - **Dependencies**: `spring-boot-devtools`, `org.hsqldb`

11. **Lombok for Simplified Code**
    - **Description**: Integrates Lombok to reduce boilerplate code, making the codebase cleaner and more concise.
    - **Dependency**: `org.projectlombok`

12. **Logging and Monitoring**
    - **Description**: Utilizes Spring Boot Actuator for logging and monitoring application health and metrics.
    - **Dependency**: `spring-boot-starter-actuator`

## Installation

To install and set up the "OnlineRetailSystem" project, follow these steps:

1. Clone the project repository from GitHub.
2. Ensure that you have Java 17 installed.
3. Build the project using Maven: `mvn clean install`
4. Run the application: `mvn spring-boot:run`

## Usage

Once the project is set up and running, you can access the online retail system via the provided endpoints. Below are some examples of how to use the system:

- Register as a new user.
- Browse and search for products.
- Add products to your shopping cart.
- Place orders and make payments.
- Manage your user account and order history.


