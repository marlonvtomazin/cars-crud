# Car CRUD API

A RESTful API built with **Java** and **Spring Boot** for managing cars.

---

## 🧠 Learning Goals

This project was designed to practice:
- Clean REST API design
- Proper exception handling
- Transaction management
- Database versioning with Flyway
- Integration and controller testing
- Docker-based infrastructure
- 
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

---

# 🚀 Next Steps (Roadmap)

- This project can evolve into a complete Workshop Management System API.
Possible next steps include:

### 🔧 Parts Management
- Register parts (name, manufacturer, price, stock quantity)
- Update stock automatically when a service is performed
- Prevent deletion of parts linked to active orders
- 
### 👤 Customer Management
- Register customers (name, document, phone, email)
- Associate customers with one or more cars
-  Search customers by name or document

### 🚗 Vehicle Ownership
- Associate cars with customers
- List all vehicles from a specific customer
- Prevent duplicate car plates across customers

### 🧾 Service Orders / Budgets
- Create service orders (maintenance, repair, inspection)
- Link cars, customers, and parts to an order
- Calculate total cost based on parts and labor
- Order status flow (OPEN → APPROVED → IN_PROGRESS → DONE)

### 🔐 Authentication & Authorization
- User authentication with JWT
- Roles (ADMIN, MECHANIC, ATTENDANT)
- Secure endpoints based on role

### 📊 Reports & Metrics
- Monthly revenue reports
- Most used parts
- Number of services per car or customer

### 🧪 Testing & Quality
- Increase integration test coverage
- Add repository tests
- Add contract tests for the API

### ☁️ Infrastructure & DevOps
- Dockerize the application (API + database)
- Environment-based configuration (dev, test, prod)
- CI pipeline (GitHub Actions)