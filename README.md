# Examly

Examly is a web application for preparing to the exams(state exam in Russia). It gives opportunities to solve questions, select subjects and their task types and filter questions with topics.

## 🎯 Main Goal:
Create an comfortable environment for preparing to the exams. It helps users:

- solve every type of questions
- select subjects and their task types
- filter questions of types with topics

## 🛠️ Stack

### Backend
- Java
- Spring Boot
- Spring Web
- Spring Data JPA (Hibernate)
- Spring Security
- JWT
- PostgreSQL
- Liquibase
- MapStruct

### Infrastructures
- Docker
- Docker Compose

### Frontend
- HTML
- CSS
- JavaScript

# Application Boot
### 🐳 Docker boot
If you running this app using docker everything you need is create `.env` file by this example:
```.evn
POSTGRES_DB=database_name
POSTGRES_USER=db_user
POSTGRES_PASSWORD=db_password
```
You don't need to connect to something or install, this properties are just saying docker create database using them values if you want you can dont even change this file before launch its not really matters

Then execute `docker compose up --build` and application will launch, you can get access to it on http://localhost:8080

### 💻 Local boot
If you don't use docker you still able to launch the application, but you must have on your machine:
- JDK 21+
- PostgreSQL(with created database)
Then you gotta create src/resources/application.yaml file by this example:
```application.yaml
spring:
  datasource:
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5432/YOUR_DB
    username: YOUR_USERNAME
    password: YOUR_PASSWORD
  liquibase:
    change-log: classpath:/db/changelog/db.changelog-master.yaml
    analytics-enabled: true

jwt:
  secret: JWT_SUPER_SECRET_KEY
  expiration: 3600000
```

# 📌 Project Status
Project is still has developing status

In the future, it's planned to add:
- seed database with more questions(i was lazy to seed em all but i added some just for you to test)
- add teacher role(will create students groups and assign homework)
- statistics by solved questions(in profile)
- AI helper