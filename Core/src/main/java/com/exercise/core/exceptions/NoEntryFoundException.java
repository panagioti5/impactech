package com.exercise.core.exceptions;

public class NoEntryFoundException extends RuntimeException {
    private final ExceptionResponse response;

    public NoEntryFoundException(ExceptionResponse response) {
        super(response.getMessage());
        this.response = response;
    }

    public ExceptionResponse getResponse() {
        return response;
    }
}
