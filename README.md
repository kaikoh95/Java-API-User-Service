# User-Service-API
An API service for creating, retrieving, updating, and deleting users as an assessment for Vector and for my own practice in Java.
Built with Maven, Spring Boot.

## Local Setup

This is built with Maven and Springboot.
To start, ensure that you have Maven command in your terminal/command prompt.
Run

```mvn spring-boot:run```

to start the local server on localhost:8080 port.

You can use Postman for testing.

API methods are based on the OpenAPI spec provided.

## Notes 

### 31 May 2021 1210

- Fixed null issues on fields

- Updated error handlers with more detailed messages

### 27 May 2021 0830

- Implemented User REST API Service

- To add unit tests

- To fix null issues on fields other than email
