package com.exercise.exceptions;

import com.exercise.core.exceptions.ExceptionResponse;

public class PbxNotFoundException extends RuntimeException {
    private final ExceptionResponse response;

    public PbxNotFoundException(ExceptionResponse response) {
        super(response.getMessage());
        this.response = response;
    }

    public ExceptionResponse getResponse() {
        return response;
    }
}
