package com.exercise.core.exceptions;

public class RuntimeExceptionImpl extends RuntimeException {
    private final ExceptionResponse response;

    public RuntimeExceptionImpl(ExceptionResponse response) {
        super(response.getMessage());
        this.response = response;
    }

    public ExceptionResponse getResponse() {
        return response;
    }
}
