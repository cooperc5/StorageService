# StorageService

StorageService is a microservice responsible for storing and retrieving application data. It could be a Spring Boot service connected to a database or cloud storage.

## Requirements

- **Java 21 (Amazon Corretto 21)** – The service now runs on JDK 21. Install Amazon Corretto 21 or another Java 21 JDK before building or running the application.
- **Maven 3.x** – To build the project.
- **Docker Desktop** – To containerize the service and its DB.

## Building the Application

Ensure Java 21 is installed and `JAVA_HOME` points to it. Then build with Maven:

## Running the Application

Ensure docker desktop is installed and running. Then use start-service.ps1 or restart-service.ps1 to start the service.

```bash
mvn clean package
