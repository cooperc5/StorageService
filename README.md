# StorageService

StorageService is a microservice responsible for storing and retrieving application data. It could be a Spring Boot service connected to a database or cloud storage.

## Requirements

- **Java 21 (Amazon Corretto 21)** – The service now runs on JDK 21. Install Amazon Corretto 21 or another Java 21 JDK before building or running the application.
- **Maven 3.x** – To build the project.
- **Database** – (If applicable, e.g., PostgreSQL running for testing, etc.)

## Building the Application

Ensure Java 21 is installed and `JAVA_HOME` points to it. Then build with Maven:

```bash
mvn clean package
