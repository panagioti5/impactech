package com.exercise.exceptions;

import com.exercise.core.exceptions.CustomizedResponseEntityExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public class CustomizedResponseEntityExceptionHandlerPhone extends CustomizedResponseEntityExceptionHandler {
    @ExceptionHandler(PhoneBookNotFoundException.class)
    public final ResponseEntity<Object> handleAllExceptions(PhoneBookNotFoundException e, WebRequest request) {
        request.getDescription(false);
        return new ResponseEntity(e.getResponse(), HttpStatus.NOT_FOUND);
    }
}
