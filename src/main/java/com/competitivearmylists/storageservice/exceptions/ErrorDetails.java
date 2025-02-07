package com.competitivearmylists.storageservice.exceptions;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.List;

public class ErrorDetails {

    private LocalDateTime timestamp; // Error occurrence time
    private String message;          // High-level error message
    private String description;      // Contextual details
    private Object details;          // Stack trace or field errors (dynamic content)

    public ErrorDetails(LocalDateTime timestamp, String message, String description, Object details) {
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }
}
