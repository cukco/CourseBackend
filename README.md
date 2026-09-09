# Course Management System - Backend API

This is a RESTful API backend for a Course Management System, built with **Spring Boot** and **Java 25**. It provides functionalities to manage students, instructors, courses, and student enrollments, secured by **JWT (JSON Web Token)** authentication.

## Technologies Used
*   **Java 25**
*   **Spring Boot 3.x** (Web, Data JPA, Security)
*   **PostgreSQL** (Database)
*   **Hibernate** (ORM)
*   **JWT (jjwt)** (Authentication & Authorization)
*   **Gradle** (Build Tool)

## Features
*   **User Authentication**: Login and registration with secure JWT tokens.
*   **Course Management**: Create, read, update, and delete courses.
*   **Instructor Management**: Manage instructors and assign them to courses.
*   **Student Management**: Manage student profiles.
*   **Enrollment System**: Handle student enrollments into specific courses.
*   **Pagination & Sorting**: Efficient data retrieval for large datasets.

## Architecture & Core Features

### 1. JWT Authentication
The project uses **JSON Web Token (JWT)** for secure, stateless authentication. 
*   **Login & Registration**: Managed in `AuthController`. Upon successful login, the server generates a JWT token using `JwtUtils` and returns it to the client.
*   **Token Verification**: `JwtAuthFilter` intercepts incoming requests, extracts the JWT from the `Authorization: Bearer <token>` header, validates its signature and expiration, and sets the `Authentication` context if valid.

### 2. USER/ADMIN Authorization
Role-based access control is implemented via Spring Security:
*   Users are assigned roles (e.g., `ROLE_STUDENT`, `ROLE_ADMIN`) when their accounts are created.
*   Endpoints are secured in `SecurityConfig`. Public endpoints like `/auth/**` and `GET /courses` are `permitAll()`, while other API routes require authentication (`authenticated()`) and can be further restricted using `@PreAuthorize("hasRole('ADMIN')")` at the controller level for administrative actions (like modifying courses or instructors).

### 3. DTO + Validation
Data Transfer Objects (DTOs) are heavily utilized to decouple the internal domain models from the external API contracts.
*   **Request/Response Separation**: Dedicated DTOs like `CourseCreateRequest`, `StudentCreateRequest`, etc., ensure that only the required fields are exposed or accepted.
*   **Jakarta Validation**: DTOs are enriched with validation annotations (e.g., `@NotBlank`, `@NotNull`, `@Email`). The controllers use the `@Valid` annotation on `@RequestBody` to automatically validate incoming JSON payloads before they reach the service layer, preventing invalid data processing.

### 4. Global Exception Handling
To ensure a consistent API response structure, all exceptions are handled centrally rather than using fragmented `try-catch` blocks across controllers.
*   **@RestControllerAdvice**: `GlobalExceptionHandler` acts as an interceptor for exceptions thrown anywhere in the application.
*   **Standardized Responses**: Exceptions like `MethodArgumentNotValidException` (from DTO validation), `AuthenticationException` (login failures), and generic `Exception`s are caught and transformed into a unified `APIResponse(success=false, message=..., data=...)` format with appropriate HTTP status codes (400 Bad Request, 401 Unauthorized, etc.).

### 5. Structured Logging
The application uses standard SLF4J with Logback for application logging.
*   **Traceability**: Critical operations, validation errors, and security events can be logged with clear contextual messages.
*   **Exception Tracking**: The `GlobalExceptionHandler` is the ideal place to log stack traces or error summaries using `log.error()`, keeping the controller code clean and ensuring all unhandled exceptions are recorded for debugging and monitoring purposes.


## Setup & Installation (Localhost)

### Prerequisites
*   [Java Development Kit (JDK) 25](https://adoptium.net/)
*   [PostgreSQL](https://www.postgresql.org/download/) installed and running on port `5432`.

### 1. Database Configuration
Create a database named `postgres` in your local PostgreSQL server, or update the `src/main/resources/application.properties` to match your existing database name, username, and password:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=123
```

### 2. Build and Run
Open your terminal at the root of the project and run the following command to start the application:

**On Windows:**
```powershell
.\gradlew.bat bootRun
```

**On Mac/Linux:**
```bash
./gradlew bootRun
```

The server will start on `http://localhost:5050`.

*(Note: The database tables will be automatically generated upon the first run thanks to `spring.jpa.hibernate.ddl-auto=update`)*

## Seeding Data
A `seed_students.sql` file is included in the project root if you want to quickly populate the database with dummy data for testing purposes. You can execute this script directly in your PostgreSQL client (like pgAdmin or DBeaver).


