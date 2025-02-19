package com.competitivearmylists.storageservice.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * Custom exception class for handling cases where a requested resource is not found.
 * This exception is thrown when an entity (e.g., a database record) cannot be located by its identifier.
 *
 * - `@ResponseStatus(HttpStatus.NOT_FOUND)`: This annotation ensures that when this exception
 *   is thrown, Spring MVC automatically returns an HTTP 404 response.
 * - Extends `RuntimeException` since resource-not-found scenarios are usually unchecked exceptions.
 */
@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L; // Ensures compatibility across different JVM instances.



    private final String entityName; // The name of the entity that was not found.
    private final Object resourceId; // The identifier of the missing resource.

    /**
     * Constructs a new ResourceNotFoundException with the entity name and resource ID.
     *
     * @param entityName The type of entity that could not be found (e.g., "User", "Product").
     * @param resourceId The ID of the entity that was requested but not found.
     */
    public ResourceNotFoundException(String entityName, Object resourceId) {
        super(entityName + " not found with ID: " + resourceId); // Provides a descriptive error message.
        this.entityName = entityName;
        this.resourceId = resourceId;
    }
}
