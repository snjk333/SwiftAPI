# SwiftApi

**Description:**

SwiftApi is an application that manages SWIFT codes (Bank Identifier Codes), which are used for international money transfers. It parses SWIFT code data, stores it in a database, and exposes a REST API for access.

### Goals:

1. **Parse SWIFT Code Data** from a file and store it efficiently:
    - Codes ending in "XXX" are headquarter codes, others are branch codes.
    - Branches are linked to headquarters by matching the first 8 characters.
    - Country codes and names should be in uppercase.

2. **Store Data** in a fast, low-latency database (MySQL).

3. **Expose a REST API** to:
    - Retrieve details of a single SWIFT code.
    - Get all SWIFT codes for a specific country.
    - Add and delete SWIFT codes.

### API Endpoints:

1. **GET /v1/swift-codes/{swift-code}** - Get details of a SWIFT code.
2. **GET /v1/swift-codes/country/{countryISO2code}** - Get SWIFT codes for a country.
3. **POST /v1/swift-codes** - Add a new SWIFT code.
4. **DELETE /v1/swift-codes/{swift-code}** - Delete a SWIFT code.

---

## Progress Report

### 1. **Database Creation:**
- Successfully created a **MySQL** database for storing SWIFT code data with fast retrieval by code and country.

### 2. **Application Development:**
- The app was built using **Spring Boot** for scalability and integration with MySQL.
- Integrated **Swagger UI** for API documentation, enabling easy endpoint testing.

### 3. **Containerization:**
- Encountered difficulties combining the **Spring Boot** app and **MySQL** in a Docker setup.
- While I could run both separately, I couldn't get them to work together in Docker. This was my first time with Docker, and I plan to revisit it later.

### 4. **Unit Tests:**
- Unfortunately, I was unable to implement **unit tests** due to lack of experience with testing frameworks. This is something I will focus on learning and adding in the future.

---

## Resources:

- [Docker Get Started](https://docs.docker.com/get-started/workshop/)
- [Spring Framework Documentation](https://docs.spring.io/spring-framework/reference/index.html)
- [Swagger UI Documentation](https://swagger.io/tools/swagger-ui/)
- [MySQL Docker Hub](https://hub.docker.com/_/mysql)
- **ChatGPT** - Used as a virtual mentor for troubleshooting and receiving guidance throughout the project. :)

---

## Swagger UI Screenshots

Here are some screenshots of the Swagger UI:

![Swagger UI 1](./src/main/resources/Swagger1.png)
![Swagger UI 2](./src/main/resources/Swagger2.png)


---

**Final Thoughts:**

Overall, this was an interesting experience. I had some previous knowledge of Spring Boot and working with databases, but using it in a more real-world project made it even more valuable. I tried different data sources and approaches, and managed to create something useful. I believe I did my best, and with more experience, I could have completed all the tasks.