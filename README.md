[![Module Timetable Management System - RESTful API cover image](./docs/cover.png)](https://app.swaggerhub.com/apis-docs/Esh07/Module-Timetable-Management-System-RESTful-API/1.0.0#/)

# Module Timetable Management System - RESTful API

## :page_facing_up: Table of Contents

- [Introduction](#introduction)
- [Technologies Used](#computer-technologies-used)
- [Features](#bulb-features)
- [Documentation](#gear-setup)
  - [Setup](#gear-setup)
  - [API Documentation](#api-documentation)

## Introduction

During my journey of learning Spring Boot, Spring Web, and RESTful API development, I took on the challenge of developing a module timetable management system for the School of Computing and Mathematical Sciences. This project allowed me to apply my knowledge of Spring Boot and RESTful API development to a real-world scenario, and I was able to learn more about the Spring framework and its capabilities.

The primary objective of this project was to design and implement an API that organizes and manages module timetables, focusing on convenor information, modules taught, and the sessions delivered within each module.

<!-- project completed  -->

:book: To get started with the project, please refer to the [installation guide]().

For the API documentation and Swagger UI, please refer to the [API Documentation](https://app.swaggerhub.com/apis-docs/Esh07/Module-Timetable-Management-System-RESTful-API/1.0.0#/) section.

> :tada: Project completed on May 2022.

## :computer: Technologies Used

- **Programming Language:** Java 11
- **Framework:** Spring Framework
- **Build Tool:** Gradle
- **IDE:** IntelliJ IDEA/VS Code
- **Database:** MySQL
- **API Testing:** Postman
- **Version Control:** Git

## :bulb: Features

**Convenor Management:**

- Add, retrieve, update, and delete convenors
- Get convenor details by ID

**Module Management:**

- Add, retrieve, update, and delete modules
- Get module details by code

**Session Management:**

- Add, retrieve, update, and delete sessions for modules
- Get session details by ID and module code

**API Documentation and Swagger UI:**

- Comprehensive documentation of API endpoints and schemas
- Swagger UI for interactive exploration and testing of the API

**Validation and Data Integrity:**

- Input data validation for convenors, modules, and sessions
- Ensuring data accuracy and integrity

## Documentation

### :gear: Setup

To get started with the project, please refer to the [installation guide]().

#### API Documentation

The API documentation provides detailed information about the available endpoints, request/response schemas, and examples. Please refer to the [API Documentation](https://app.swaggerhub.com/apis-docs/Esh07/Module-Timetable-Management-System-RESTful-API/1.0.0#/) for more details.

<!-- // key learning points -->

## :key: Key Learning

**Designing a RESTful API:**
One of the skills I acquired was how to design and implement a RESTful API that follows industry best practices. I used [appropriate HTTP methods (GET, POST, PUT, DELETE) and conventions](https://restfulapi.net/http-methods/) to create an API that supports CRUD operations for managing modules, sessions, and convenors. I also learned the importance of [using proper HTTP status codes](https://en.wikipedia.org/wiki/List_of_HTTP_status_codes) to communicate the result of API calls and provide feedback to the client.

**Controller Components:**

Another skill I developed was how to define methods and handle requests, interact with repositories, and return accurate responses. I used annotations like `@RestController`, `@GetMapping`, `@PostMapping`, `@PutMapping`, and `@DeleteMapping` to map URLs to controller methods.

**Spring Boot and Spring Web:**

I learned to make a RESTful API using Spring Boot and Spring Web. I used `@RestController` to make RESTful endpoints and `@RequestMapping` to connect HTTP requests to methods. I also used `@PathVariable` to get path variables from the request URI and `@RequestBody` to get the request body.

**Implementing the Repository Pattern:**

I also learned how to implement the repository pattern to separate the business logic from the data access logic. I used the `@Repository` annotation to create a repository interface (`ModuleRepository`, `SessionRepository`, `ConvenorRepository`) that extends the JpaRepository interface. This allowed me to use the methods provided by the [JpaRepository](https://docs.spring.io/spring-data/data-jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html) interface to perform CRUD operations on the database. I also used the `@Autowired` annotation to inject the repository into the controller.

**Model Classes:**

Creating model classes to represent the data in the database was also part of what I learned. I used the `@Entity` annotation to create an entity class (`Module`, `Session`, `Convenor`) that maps to a table in the database. I also used the `@Id` annotation to specify the primary key.

**Exception Handling:**

I learned to implement custom exception handling in my application. By creating a custom `BadRequestException` class that extends `RuntimeException`, I was able to handle invalid input data scenarios. I used the `@ExceptionHandler` annotation in the `BadRequestAdvice` class to catch instances of `BadRequestException` and return error information. The `@ResponseBody` annotation allowed me to send the error message as the response body. I used the `@ResponseStatus(HttpStatus.BAD_REQUEST)` annotation to set the HTTP status code for the response.

**Data Persistence:**

I learned how to build a database and store data using MySQL. This included developing a database structure, generating tables, and populating them with data. To provide column names and primary key creation methods, I additionally used annotations like `@Column` and `@GeneratedValue`.

**Dependency Injection:**

The last skill I learned was how to use Spring Data JPA to interact with the database. I used the `@Repository` annotation to create a repository interface, and the `@Autowired` annotation to inject the repository into the controller. I also used the `@Entity` annotation to create an entity class, and the `@Id` annotation to specify the primary key."
