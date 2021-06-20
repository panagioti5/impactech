package com.exercise.exceptions;

import com.exercise.core.exceptions.ExceptionResponse;

public class CdrNotFoundException extends RuntimeException {
    private final ExceptionResponse response;

    public CdrNotFoundException(ExceptionResponse response) {
        super(response.getMessage());
        this.response = response;
    }

    public ExceptionResponse getResponse() {
        return response;
    }
}
