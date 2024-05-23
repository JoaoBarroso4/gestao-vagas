# Vacancy Management
This is a simple web application that allows users to manage job vacancies. It was developed using Java Spring Boot. This app is based on a RockeatSeat company project. It uses Spring Data JPA (MySQL database for development and H2 for testing) to manage the database, Spring Security for role-based access and JWT Token validation. For testing, it uses JUnit and Mockito.

## Requirements
- Java 17
- Spring Boot 3
- Maven
- Docker

## Installation
1. Clone the repository.
```bash
git clone https://github.com/JoaoBarroso4/gestao-vagas.git
```
2. Navigate to the project folder.
3.Build the project.
```bash
mvn clean install
```
4. Run docker-compose
```bash
docker-compose up -d
```
5. Run the project.
```bash
mvn spring-boot:run
```
The program will be available at `http://localhost:8080`. Docs at `http://localhost:8080/swagger-ui/index.html/`.

## Endpoints

### Candidate
- `POST /candidate/`: Create a new candidate.
- `POST /candidate/auth/`: Generates a JWT token for a candidate user.
- `POST /candidate/job/apply`: Create a new job application.
- `GET /candidate/`: Get a list of all candidates.
- `GET /candidate/job/`: Get a list of all job applications for a candidate.

### Company
- `POST /company/`: Create a new company.
- `POST /company/auth/`: Generates a JWT token for a company user.
- `POST /company/job/`: Create a new job vacancy.

## Testing
To run the tests, use the following command:
```bash
mvn test
```