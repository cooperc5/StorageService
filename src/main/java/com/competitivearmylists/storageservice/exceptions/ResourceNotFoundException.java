package com.competitivearmylists.storageservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final String entityName;
    private final Object resourceId;

    public ResourceNotFoundException(String entityName, Object resourceId) {
        super(entityName + " not found with ID: " + resourceId);
        this.entityName = entityName;
        this.resourceId = resourceId;
    }

    public String getEntityName() {
        return entityName;
    }

    public Object getResourceId() {
        return resourceId;
    }
}
