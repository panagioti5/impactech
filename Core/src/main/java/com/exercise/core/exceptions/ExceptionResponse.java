package com.exercise.core.exceptions;

import com.exercise.core.constants.ErrorCode;

import java.util.Date;

public class ExceptionResponse {
    private String message;
    private ErrorCode errorCode;
    private Date date;

    public ExceptionResponse(String message, ErrorCode errorCode, Date date) {
        this.message = message;
        this.errorCode = errorCode;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}