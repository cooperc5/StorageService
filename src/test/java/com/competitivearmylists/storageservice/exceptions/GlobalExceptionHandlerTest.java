package com.competitivearmylists.storageservice.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void testHandleResourceNotFound() {
        // Arrange
        ResourceNotFoundException exception = new ResourceNotFoundException("TestEntity", "12345");
        WebRequest request = mock(WebRequest.class);
        when(request.getDescription(false)).thenReturn("/test/resource");

        // Act
        ResponseEntity<ErrorDetails> response = globalExceptionHandler.handleResourceNotFound(exception, request);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).isEqualTo("TestEntity not found with ID: 12345");
        assertThat(response.getBody().getDescription()).isEqualTo("Resource not found at /test/resource");
        assertThat(response.getBody().getTimestamp()).isNotNull();
        assertThat(response.getBody().getDetails()).isNull();
    }

    @Test
    void testGlobalExceptionHandler() {
        // Arrange
        Exception exception = new RuntimeException("Unexpected error");
        WebRequest request = mock(WebRequest.class);
        when(request.getDescription(false)).thenReturn("/test/global");

        // Act
        ResponseEntity<ErrorDetails> response = globalExceptionHandler.globalExceptionHandler(exception, request);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).isEqualTo("Unexpected error");
        assertThat(response.getBody().getDescription()).isEqualTo("An unexpected error occurred at /test/global");
        assertThat(response.getBody().getTimestamp()).isNotNull();
        assertThat(response.getBody().getDetails()).asList().isNotEmpty();
    }

    @Test
    void testHandleValidationExceptions() {
        // Arrange
        MethodArgumentNotValidException exception = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        when(exception.getBindingResult()).thenReturn(bindingResult);

        FieldError fieldError1 = new FieldError("object", "field1", "must not be empty");
        FieldError fieldError2 = new FieldError("object", "field2", "must be a valid email");
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError1, fieldError2));

        HttpHeaders headers = new HttpHeaders();
        WebRequest request = mock(WebRequest.class);
        when(request.getDescription(false)).thenReturn("/test/validation");

        // Act
        ResponseEntity<Object> response = globalExceptionHandler.handleMethodArgumentNotValid(
                exception, headers, HttpStatus.BAD_REQUEST, request);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();

        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        assertThat(responseBody.get("error")).isEqualTo("Validation Failed");
        assertThat(responseBody.get("errors")).asList().contains(
                "field1: must not be empty",
                "field2: must be a valid email"
        );
        assertThat(responseBody.get("timestamp")).isNotNull();
        assertThat(responseBody.get("status")).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
