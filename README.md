# EduConnect

**An online course system backend using Spring Boot**

## Features
- User can register and log in.
- Authentication using JWT.
- Only students can enroll in courses.
- Exception handling.
- Instructors and admins can monitor students' activities.
- No dupicate entries

## Tech Stack
- Spring Boot
- PostgreSQL
- JWT
- Postman (for testing)

## Setup Instructions
1. **Database**: Make sure you have PostgreSQL running locally and create a database for the project.
2. **Environment Configuration**: Set up your `application.properties` with the necessary details for connecting to the PostgreSQL database and JWT secret.
3. **API Tester**: Use Postman to test the APIs (authentication, enrollment, etc.).
4. **Run the Application**: Once the setup is complete, you can run the application with `mvn spring-boot:run` or via your IDE.

## How to Use
1. **Register** a new user using the `/api/auth/register` endpoint.
2. **Login** using the `/api/auth/login` endpoint to obtain a JWT token.
3. Use the JWT token to access protected resources like course enrollment, etc.

## Contribution
Feel free to fork the repository and make changes. If you'd like to contribute, please create a pull request.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Portfolio Project
This project, EduConnect, is part of my portfolio to showcase my skills in backend development using Spring Boot and PostgreSQL. It demonstrates my ability to design and build a fully functional system with JWT-based authentication, exception handling, and role-based access control for users, instructors, and administrators.
