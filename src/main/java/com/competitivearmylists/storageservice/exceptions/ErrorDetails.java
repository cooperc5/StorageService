package com.competitivearmylists.storageservice.exceptions;

import java.util.Date;

public class ErrorDetails {
    private String description;
    private String errorMessage;
    private Date date;
    private String stackTrace;

    public ErrorDetails(Date date, String errorMessage, String description, String stackTrace) {
        this.description = description;
        this.errorMessage = errorMessage;
        this.date = date;
        this.stackTrace = stackTrace;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}
