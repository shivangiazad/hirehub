# HireHub - Job Board & Internship Tracker

A full-stack Job Board web application built with Java Spring Boot backend and HTML/CSS/JS frontend.

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

## Project Structure

```
src/main/java/com/hirehub/hirehub/
├── controller/ REST API endpoints
├── model/ Entity classes
├── repository/ Database access layer
├── security/ JWT and security configuration
└── config/ Application configuration
```

## How to Run Locally

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