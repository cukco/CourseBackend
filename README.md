# Course Management System - Backend API

This is a RESTful API backend for a Course Management System, built with **Spring Boot** and **Java 25**. It provides functionalities to manage students, instructors, courses, and student enrollments, secured by **JWT (JSON Web Token)** authentication.

## 🚀 Technologies Used
*   **Java 25**
*   **Spring Boot 3.x** (Web, Data JPA, Security)
*   **PostgreSQL** (Database)
*   **Hibernate** (ORM)
*   **JWT (jjwt)** (Authentication & Authorization)
*   **Gradle** (Build Tool)

## 📦 Features
*   **User Authentication**: Login and registration with secure JWT tokens.
*   **Course Management**: Create, read, update, and delete courses.
*   **Instructor Management**: Manage instructors and assign them to courses.
*   **Student Management**: Manage student profiles.
*   **Enrollment System**: Handle student enrollments into specific courses.
*   **Pagination & Sorting**: Efficient data retrieval for large datasets.

## 🛠️ Setup & Installation (Localhost)

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

## 🗃️ Seeding Data
A `seed_students.sql` file is included in the project root if you want to quickly populate the database with dummy data for testing purposes. You can execute this script directly in your PostgreSQL client (like pgAdmin or DBeaver).

## 📄 License
This project is for educational purposes.
