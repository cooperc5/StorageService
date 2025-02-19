package com.competitivearmylists.storageservice.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorDetails {

    private LocalDateTime timestamp; // Error occurrence time
    private String message;          // High-level error message
    private String description;      // Contextual details
    private String details;          // Stack trace or field errors (dynamic content)

    public ErrorDetails(LocalDateTime timestamp, String message, String description, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
        this.details = details;
    }

}
