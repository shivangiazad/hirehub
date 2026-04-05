# HireHub - Job Board & Internship Tracker

A full-stack Job Board web application built with Java Spring Boot backend and HTML/CSS/JS frontend.

## 🌐 Live Demo

[https://hirehub-frontend-ux7z.onrender.com](https://hirehub-frontend-ux7z.onrender.com)

## Features

- JWT-based Authentication (Register/Login)
- 3 User Roles: Student, Company, Admin
- Post & Browse Job Listings
- Apply for Jobs with Resume Upload (PDF)
- Track Application Status (Applied/Shortlisted/Rejected)
- Search & Filter Jobs
- Pagination
- Admin Dashboard with Stats
- Responsive UI with Bootstrap

## Tech Stack

| Layer          | Technology                                |
|----------------|-------------------------------------------|
| Backend        | Java 25, Spring Boot 3.5, Spring Security |
| Authentication | JWT (JSON Web Tokens)                     |
| Database       | MySQL with Spring Data JPA                |
| Frontend       | HTML, CSS, JavaScript, Bootstrap 5        |
| Tools          | Postman, Git, Maven, VS Code              |

## 📁 Project Structure
```
src/main/java/com/hirehub/hirehub/
├── config/
│   └── CorsConfig.java          → CORS configuration
├── controller/
│   ├── AuthController.java      → Register & Login APIs
│   ├── JobController.java       → Job CRUD APIs
│   ├── ApplicationController.java → Apply & track APIs
│   └── AdminController.java     → Admin dashboard APIs
├── model/
│   ├── User.java                → User entity (STUDENT/COMPANY/ADMIN)
│   ├── Job.java                 → Job listing entity
│   └── Application.java        → Job application entity
├── repository/
│   ├── UserRepository.java
│   ├── JobRepository.java
│   └── ApplicationRepository.java
└── security/
    ├── JwtUtil.java             → JWT token generation & validation
    ├── JwtFilter.java           → JWT request filter
    └── SecurityConfig.java      → Spring Security configuration
```

## 🚀 How to Run Locally

### Prerequisites
- Java 21+
- MySQL (XAMPP)
- Maven
- VS Code or IntelliJ

### Steps
1. Clone the repo
git clone https://github.com/shivangiazad/hirehub.git
cd hirehub

2. Start XAMPP → Start **Apache** and **MySQL**

3. Open `http://localhost/phpmyadmin` → Create database named `hirehub`

4. Open `src/main/resources/application.properties` and update:
```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/hirehub
   spring.datasource.username=root
   spring.datasource.password=
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
   server.port=8080
```

5. Run the backend
mvn spring-boot:run

6. Open `index.html` with **Live Server** in VS Code

7. Register an account and start exploring! 🎉

### Prerequisites

- Java (JDK 17 or above recommended)
- MySQL
- Maven

### Steps

1. Clone the repository:
git clone https://github.com/shivangiazad/hirehub.git


2. Navigate to the project directory:
cd hirehub


3. Create a MySQL database:
hirehub


4. Update database configuration in:
src/main/resources/application.properties


5. Run the application:
mvn spring-boot:run


6. Open `index.html` using Live Server or browser.

## API Endpoints

| Method | Endpoint             | Description                      |
|--------|----------------------|----------------------------------|
| POST   | /api/auth/register   | Register new user                |
| POST   | /api/auth/login      | Authenticate user and return JWT |
| GET    | /api/jobs            | Get all jobs (with pagination)   |
| POST   | /api/jobs            | Create a new job                 |
| POST   | /api/jobs/{id}/apply | Apply to a job                   |
| GET    | /api/my/applications | Get user applications            |
| GET    | /api/admin/stats     | Admin dashboard data             |

## Future Improvements

- Email notifications
- Resume parsing
- Advanced filtering and sorting
- UI improvements and full frontend integration
- Deployment on cloud (AWS/Render)

## Author

Shivangi Azad
GitHub: https://github.com/shivangiazad