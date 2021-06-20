package com.exercise.core.exceptions;


import com.exercise.core.constants.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleAllExceptions(ConstraintViolationException e, WebRequest request) {
        StringBuilder sbIssues = new StringBuilder();
        e.getConstraintViolations().forEach(x -> sbIssues.append(x.getMessage()).append(";"));
        ExceptionResponse exceptionResponse = new ExceptionResponse(sbIssues.toString(), ErrorCode.FAILURE, new Date());
        request.getDescription(false);
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoEntryFoundException.class)
    public final ResponseEntity<Object> handleAllExceptions(NoEntryFoundException e, WebRequest request) {
        request.getDescription(false);
        return new ResponseEntity(e.getResponse(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeExceptionImpl.class)
    public final ResponseEntity<Object> handleAllExceptions(RuntimeExceptionImpl e, WebRequest request) {
        request.getDescription(false);
        return new ResponseEntity(e.getResponse(), HttpStatus.BAD_REQUEST);
    }
}
