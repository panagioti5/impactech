package com.exercise.exceptions;

import com.exercise.core.exceptions.ExceptionResponse;

public class PhoneBookNotFoundException extends RuntimeException {
    private final ExceptionResponse response;

    public PhoneBookNotFoundException(ExceptionResponse response) {
        super(response.getMessage());
        this.response = response;
    }

    public ExceptionResponse getResponse() {
        return response;
    }
}