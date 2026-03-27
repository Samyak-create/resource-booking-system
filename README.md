# Grind - Resource Booking System

A comprehensive Full-Stack application designed for managing resources and internal bookings. The system offers standard access for regular users to view and book resources, as well as an Admin platform designed for administrative oversight, resource insertion, and removal. 

## Tech Stack

### Frontend 
- **Framework**: React.js with Vite
- **Routing**: `react-router-dom`
- **Networking**: `axios` for streamlined backend integration
- **Styling**: Pure CSS (`Register.css`, `App.css`)
- **Containerization**: Includes a `Dockerfile` properly configured for deployment via Nginx or equivalent.

### Backend 
- **Framework**: Spring Boot (Java 17)
- **Database**: MySQL relational database (with integrated Spring Data JPA)
- **Authentication**: JWT-based stateless authentication (`jjwt`, BCrypt, Spring Security)
- **Containerization**: Packaged natively with a `Dockerfile` for streamlined microservices operation.

## Architecture & Features

This platform is divided symmetrically between a Vite Frontend and a Spring Backend, communicating via RESTful JSON APIs.

### Key Components

- **JWT Authentication Flow**: Users undergo registration leading into credential-based authentication using HTTP standard JWT distribution. Passwords are securely hashed using BCrypt.
- **Resource Management (Admins)**: A secure `ROLE_ADMIN` domain mapping allowing staff to effortlessly query `/api/resources` endpoints to `POST` new stock or `DELETE` existing entries.
- **Resource Booking (Users)**: Standard user profiles navigate an intuitive graphical interface seamlessly querying backend stocks, evaluating inventory, and executing reservations securely over `/api/bookings`.

## Getting Started

1. **Database Requirements**: Establish a local or containerized MySQL engine on `localhost:3306` provisioning the `resource_db` database mapping.
2. **Backend**: 
   - Enter `Backend/Resource-Service`
   - Run the application natively using `mvn clean compile spring-boot:run` or execute `mvn clean package` and boot the `.jar`.
   - The application binds to port `:8081` natively.
3. **Frontend**:
   - Enter `Frontend/Frontend`
   - Install local node packages running: `npm install`
   - Start Vite environment using: `npm run dev`

### Securing the Setup
The backend includes a supplementary `insert_admin.sql` script specifically curated for injecting the initial master **ROLE_ADMIN** profile directly into MySQL (since admin registration requires DB-level manual provisioning to prevent unauthorized elevation). 
Run the script against your database to use: `admin@grind.com` / `admin123`.
