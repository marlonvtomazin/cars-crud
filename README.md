# Car CRUD API

A RESTful API built with **Java** and **Spring Boot** for managing cars.

---

## 📌 Features

- Create a new car
- Retrieve a car by ID
- Retrieve all cars
- Partially update a car (PATCH)
- Delete a car
- Validation with Bean Validation
- Proper HTTP status handling (201, 409, 422, etc.)
- Database versioning with Flyway

---

## 🛠️ Technologies Used

- **Java 25**
- **Spring Boot 3**
- Spring Web (REST API)
- Spring Data JPA (Hibernate)
- Flyway (database migrations)
- MySQL 8
- Lombok
- Maven
- Docker & Docker Compose (database only)
- JUnit 5 / Mockito / WebTestClient

---

## 🏗️ Architecture Overview
```text
cars-crud
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.marlonvtomazin.carscrud
│   │   │       ├── config            # Application configuration (timezone, etc.)
│   │   │       ├── entity            # JPA entities
│   │   │       ├── repository        # Spring Data repositories
│   │   │       ├── service           # Business logic
│   │   │       ├── web               # Web layer
│   │   │       │   ├── controller    # REST controllers
│   │   │       │   ├── dto           # Request/Response DTOs
│   │   │       │   └── exception     # Web layer exceptions & handlers
│   │   │       └── CarsCrudApplication.java
│   │   │
│   │   └── resources
│   │       └── db
│   │           └── migration
│   │               └── V1__create_table_cars.sql
│   │
│   └── test
│       └── java
│           └── com.marlonvtomazin.carscrud
│               ├── config
│               ├── web
│               │   └── controller
│               │       └── CarControllerTest.java
│               └── CarIT.java
│
├── docker-compose.yml
├── postman
├── pom.xml
└── .gitignore


```
---

## ▶️ Running the Application

#### Start MySQL with Docker
```
docker compose up -d
```
Run the application from IntelliJ or via:
```
./mvnw spring-boot:run
```
#### Api will be available on
http://localhost:8080/api/v1/cars

---

## 🗄️ Database

- **Database:** MySQL
- **Schema:** `cars_db`
- **Table:** `cars`
- Table creation and evolution are managed by **Flyway**

---

### Flyway behavior

- Flyway runs **automatically on application startup**
- Migrations are located at:
  - cars-crud\src\main\resources\db\migration

---

### 📖 API Documentation (Swagger)
http://localhost:8080/docs-carscrud.html

#### Postman collection available in the folder:
- postman/