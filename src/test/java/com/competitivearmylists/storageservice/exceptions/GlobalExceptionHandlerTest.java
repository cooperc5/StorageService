package com.competitivearmylists.storageservice.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for GlobalExceptionHandler.
 */
class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;
    private WebRequest request;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
        request = mock(WebRequest.class);
    }

    /**
     * Tests that validation errors are properly handled.
     * Ensures field-specific error messages are captured
     * and returned in the response with status 400 (Bad Request).
     */
    @Test
    void testHandleValidationExceptions() {
        // Arrange
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        when(exception.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(
                new FieldError("object", "field1", "must not be empty"),
                new FieldError("object", "field2", "must be a valid email")
        ));
        when(request.getDescription(false)).thenReturn("/test/validation");

        // Act
        ResponseEntity<Object> response = globalExceptionHandler.handleMethodArgumentNotValid(
                exception, null, HttpStatusCode.valueOf(400), request);

        // Assert
        assert response != null;
        assertThat(response.getStatusCode().value()).isEqualTo(400);
        assertThat(response.getBody()).isNotNull();
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        assertThat(responseBody.get("description")).isEqualTo("/test/validation");
        assertThat(responseBody.get("details")).isNotNull();
    }

    /**
     * Tests that a resource not found exception is properly handled.
     * Ensures the response contains a 404 (Not Found) status
     * and an appropriate error message.
     */
    @Test
    void testHandleResourceNotFound() {
        // Arrange
        ResourceNotFoundException exception = new ResourceNotFoundException("Test resource not found", "12345");
        when(request.getDescription(false)).thenReturn("/test/resource");

        // Act
        ResponseEntity<ErrorDetails> response = globalExceptionHandler.handleResourceNotFound(exception, request);

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(404);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getDescription()).isEqualTo("Resource not found at /test/resource");
    }

    /**
     * Tests that a general exception is properly handled.
     * Ensures the response contains a status of 500 (Internal Server Error),
     * an appropriate error message, and a valid description.
     */
    @Test
    void testHandleGlobalException() {
        // Arrange
        Exception exception = new RuntimeException("Unexpected error");
        when(request.getDescription(false)).thenReturn("/test/global");

        // Act
        ResponseEntity<ErrorDetails> response = globalExceptionHandler.handleGlobalException(exception, request);

        // Assert
        assertThat(response.getStatusCode().value()).isEqualTo(500);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).isEqualTo("An unexpected error occurred");
        assertThat(response.getBody().getDescription()).isEqualTo("/test/global");
    }
}
