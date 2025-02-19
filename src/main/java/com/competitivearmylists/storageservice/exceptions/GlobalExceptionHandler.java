package com.competitivearmylists.storageservice.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Global exception handler for handling application-wide exceptions.
 * This class extends ResponseEntityExceptionHandler to handle validation and runtime errors globally.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles validation failures for request bodies and query parameters.
     * Overrides the default implementation to provide custom error details.
     *
     * @param ex      The exception thrown when validation fails.
     * @param headers HTTP headers of the request.
     * @param status  The HTTP status code.
     * @param request The web request that resulted in the exception.
     * @return A ResponseEntity containing custom error details.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        // Collect field validation errors
        Map<String, String> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> Objects.requireNonNullElse(error.getDefaultMessage(), "Unknown validation error"),
                        (existing, replacement) -> existing // Prevents duplicate keys
                ));

        // Create error response details
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                "Validation Failed",
                request.getDescription(false),
                fieldErrors.toString() // Convert to String to align with ErrorDetails update
        );

        return new ResponseEntity<>(errorDetails, headers, status);
    }

    /**
     * Handles generic exceptions and returns a 500 response with stack trace details.
     *
     * @param ex      The exception that occurred.
     * @param request The web request that resulted in the exception.
     * @return A ResponseEntity containing error details including stack trace.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex, WebRequest request) {

        // Convert stack trace to a readable format
        List<String> stackTrace = Stream.of(ex.getStackTrace())
                .map(StackTraceElement::toString)
                .toList();

        // Create error response details
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                "An unexpected error occurred",
                request.getDescription(false),
                stackTrace.toString() // Convert list to String
        );

        return new ResponseEntity<>(errorDetails, HttpStatusCode.valueOf(500));
    }

    /**
     * Handles resource not found exceptions and returns a 404 response.
     *
     * @param ex      The ResourceNotFoundException thrown when a resource is not found.
     * @param request The web request that resulted in the exception.
     * @return A ResponseEntity containing error details.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                ex.getMessage(),
                "Resource not found at " + request.getDescription(false),
                null // No stack trace needed for 404 errors
        );
        return new ResponseEntity<>(errorDetails, HttpStatusCode.valueOf(404));
    }
}
