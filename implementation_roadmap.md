# 🗺️ Resource Booking System: Upscaling Roadmap

This is your step-by-step, structured guide to transforming your current monolithic application into a proper modern, secure, and containerized React + Spring Boot application. 

Follow these phases sequentially so you can build on top of each step safely.

---

## **Phase 1: Backend Foundation (Refactoring)**
*Before adding security, we need to make sure your core backend follows best practices.*

### **Step 1.1: Implement DTOs (Data Transfer Objects)**
Currently, your controllers return and accept raw Entities (like [Resource](file:///c:/Users/user/OneDrive/Desktop/Grind/Backend/Resource-Service/src/main/java/com/example/demo/controller/ResourceController.java#33-38) and `Booking`).
*   **Action**: Create a `dto` package. Make classes like `ResourceDTO` and `BookingRequestDTO`.
*   **How to do it in this project**: 
    1. Create `ResourceDTO.java` (fields: `id`, `name`, `availableQuantity`).
    2. In [ResourceController.java](file:///c:/Users/user/OneDrive/Desktop/Grind/Backend/Resource-Service/src/main/java/com/example/demo/controller/ResourceController.java), change `public String addResource(@RequestBody Resource res)` to `public String addResource(@RequestBody ResourceDTO resDto)`.
    3. Update `ResourceService` to convert `ResourceDTO` to a [Resource](file:///c:/Users/user/OneDrive/Desktop/Grind/Backend/Resource-Service/src/main/java/com/example/demo/controller/ResourceController.java#33-38) entity before saving it to the database.

### **Step 1.2: Add Global Exception Handling**
Currently, if an error happens (e.g., booking a resource that doesn't exist), Spring throws an ugly Java stack trace.
*   **Action**: Create an `exception` package. 
*   **How to do it in this project**:
    1. Create a custom exception class: `ResourceNotFoundException.java`.
    2. Create a global handler: `GlobalExceptionHandler.java` annotated with `@ControllerAdvice`.
    3. Inside the handler, write a method annotated with `@ExceptionHandler(ResourceNotFoundException.class)` that returns a clean `ResponseEntity` with a JSON message (e.g., `{"error": "Resource not found", "status": 404}`).

---

## **Phase 2: Security & Authentication (Spring Security + JWT)**
*Now that the backend is clean, let's secure it so only authorized employees can book resources.*

### **Step 2.1: Update the Employee Entity**
*   **Action**: Add security fields to the `Employee` entity.
*   **How to do it in this project**: Add `password` (String) and `role` (String, e.g., "ROLE_USER", "ROLE_ADMIN") to [Employee.java](file:///c:/Users/user/OneDrive/Desktop/Grind/Backend/Resource-Service/src/main/java/com/example/demo/entities/Employee.java). Create an `AuthController` for user registration where the password gets hashed using `BCryptPasswordEncoder`.

### **Step 2.2: Add Spring Security & JWT**
*   **Action**: Add `spring-boot-starter-security` and `jjwt` dependencies to [pom.xml](file:///c:/Users/user/OneDrive/Desktop/Grind/Backend/Resource-Service/pom.xml).
*   **How to do it in this project**:
    1. Create a `security` package.
    2. Implement `JwtUtil.java` to generate and validate JWT tokens.
    3. Create a `JwtFilter.java` that intercepts requests, reads the `Authorization: Bearer <token>` header, and sets the Security Context.
    4. Create `SecurityConfig.java` to configure which endpoints are public (e.g., `/api/auth/login`) and which are secured (e.g., `/api/bookings`).

---

## **Phase 3: Frontend Rewrite (React)**
*Your backend is now a secure API. Let's build a modern frontend to consume it.*

### **Step 3.1: Initialize the React App**
*   **Action**: Replace the static HTML/JS files with a React application.
*   **How to do it in this project**: 
    1. Open a terminal in your project root and run: `npx create-react-app frontend-react` (or use Vite: `npm create vite@latest frontend-react -- --template react`).
    2. Install Axios for API calls: `npm install axios`.
    3. Install React Router for navigation: `npm install react-router-dom`.

### **Step 3.2: Build the UI Components & Handle JWT**
*   **Action**: Create the React components.
*   **How to do it in this project**:
    1. Create a `Login.jsx` component that sends credentials to `/api/auth/login`. On success, save the returned JWT to `localStorage`.
    2. Create a `Dashboard.jsx` component that fetches `/api/resources`. Configure Axios to attach the JWT from `localStorage` to every request header.
    3. Create a `BookingForm.jsx` (replacing your current [bookingpage.html](file:///c:/Users/user/OneDrive/Desktop/Grind/Frontend/bookingpage.html) logic).

---

## **Phase 4: Testing & Quality Assurance**
*Ensure your new features work correctly and don't break in the future.*

### **Step 4.1: Backend Testing (JUnit + Mockito)**
*   **Action**: Add unit tests for your Services.
*   **How to do it in this project**:
    1. Go to `src/test/java/.../service/`.
    2. Create `BookingServiceTest.java`.
    3. Use `@Mock` on `BookingRepo` and `ResourceRepo`, and `@InjectMocks` on `BookingServiceImpl`.
    4. Write a test asserting that attempting to book an unavailable resource throws your new `ResourceNotFoundException`.

---

## **Phase 5: DevOps (Docker & CI/CD)**
*Make your application easy to share, deploy, and maintain.*

### **Step 5.1: Dockerization**
*   **Action**: Containerize your backend, frontend, and database.
*   **How to do it in this project**:
    1. **Backend**: Create a `Dockerfile` in `Backend/Resource-Service/` that uses `openjdk:17-jdk-alpine` to run your generated `.jar`.
    2. **Frontend**: Create a `Dockerfile` in your new React folder using `nginx` to serve the static built React files.
    3. **Docker Compose**: Create a `docker-compose.yml` file in the root directory that defines 3 services: `mysql-db`, `spring-backend`, and `react-frontend`. Now you can run the whole project with `docker-compose up`.

### **Step 5.2: CI/CD Pipeline (GitHub Actions)**
*   **Action**: Automate testing and building.
*   **How to do it in this project**:
    1. Create a `.github/workflows/main.yml` file.
    2. Write steps to: checkout code, set up Java 17, build the project (`mvn clean package`), and run the tests automatically every time you push code to GitHub.

---
> [!TIP]  
> Start with **Phase 1**. Open your IDE, create a `dto` package, build `ResourceDTO.java`, and update your controller! Work on one step at a time, test it, and then move to the next.
