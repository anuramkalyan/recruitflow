# RecruitFlow

RecruitFlow is a recruitment and applicant tracking REST API built with Java 21, Spring Boot, Spring Data JPA, Hibernate, and MySQL.

## Prerequisites

- JDK 21
- Maven 3.9+ (or use the Maven wrapper if you add one)
- Docker Desktop / Docker Engine with Compose
- Postman or curl

## Run MySQL

```bash
docker compose up -d
```

## Run the API

```bash
mvn spring-boot:run
```

The API runs at `http://localhost:8080`.

## Candidate endpoints

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/candidates` | Create a candidate |
| GET | `/api/candidates/{id}` | Get one candidate |
| GET | `/api/candidates?page=0&size=10` | List candidates with pagination |
| PUT | `/api/candidates/{id}` | Replace candidate details |
| DELETE | `/api/candidates/{id}` | Delete a candidate |

## Example request

```bash
curl -i -X POST http://localhost:8080/api/candidates \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "Rahul Sharma",
    "email": "rahul@example.com",
    "phone": "9876543210",
    "skills": "Java, Spring Boot, MySQL",
    "experienceYears": 0
  }'
```

## Configuration

Defaults are for local development only. Override `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` using environment variables for other environments. Never commit real credentials.

## Current milestone

Candidate CRUD, pagination, request validation, duplicate-email handling, and consistent API errors. Next: automated tests, then client and job modules.
