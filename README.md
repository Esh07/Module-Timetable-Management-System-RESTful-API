<div align="center">
  
[![Module Timetable Management System - RESTful API cover image](./docs/cover.png)](https://app.swaggerhub.com/apis-docs/Esh07/Module-Timetable-Management-System-RESTful-API/1.0.0#/)

# 🗓️ Module Timetable REST API

**A Spring Boot REST API for managing university module timetables, convenors, and teaching sessions**


[![Java](https://img.shields.io/badge/Java-11-ED8B00?logo=openjdk&logoColor=white)](https://openjdk.org)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-2.4.2-6DB33F?logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.x-4479A1?logo=mysql&logoColor=white)](https://mysql.com)
[![Gradle](https://img.shields.io/badge/Gradle-6.x+-02303A?logo=gradle&logoColor=white)](https://gradle.org)
[![Swagger](https://img.shields.io/badge/Swagger-OpenAPI(3.x)-85EA2D?logo=swagger&logoColor=black)](https://swagger.io)
</div>

---

## 🧭 Overview

A RESTful API built with Java and Spring Boot for managing the full lifecycle of university module timetabling — including convenors, modules, and individual teaching sessions.

Designed around clean REST principles, with input validation, structured error responses, and Swagger/OpenAPI documentation.



## ✨ Features

| Resource | Operations |
|---|---|
| 👨‍🏫 Convenors | Create, read, update, delete convenor records |
| 📚 Modules | Manage module metadata, assign/update convenors |
| 🕐 Sessions | Create and manage teaching sessions per module |
| ✅ Validation | Structured error responses for invalid or missing input |
| 📖 Docs | Interactive Swagger UI at `/swagger-ui.html` |

## 🛠 Tech Stack

- **Language:** Java 11
- **Framework:** Spring Framework, Spring Data JPA, Spring MVC
- **Database:** MySQL 8.x
- **ORM**: Hibernate via Spring Data JPA
- **Build Tool:** Gradle 6.0+
- **Docs**: Swagger / OpenAPI ORM
- **API Testing:** Postman
- **Version Control:** Git

## :gear: Getting Started

To get started with the project, please refer to the [installation guide](./Documentation.md#getting-started).

## API Documentation

The API documentation provides detailed information about the available endpoints, request/response schemas, and examples. Please refer to the [API Documentation](https://app.swaggerhub.com/apis-docs/Esh07/Module-Timetable-Management-System-RESTful-API/1.0.0#/) for more details.

## 📋 Project Status
Coursework Archive Demonstrates Spring Boot REST API design, Spring Data JPA, MySQL integration, validation, Gradle builds, and Swagger documentation.

## License

Licensed under the [MIT License](./LICENSE).
