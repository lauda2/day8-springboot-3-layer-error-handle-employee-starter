package com.example.demo.controller.advice;

import com.example.demo.exception.InvalidEmployeeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseException responseStatusExceptionHandler(Exception e) {
        return new ResponseException(e.getMessage());
    }

    @ExceptionHandler(InvalidEmployeeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public InvalidEmployeeException invalidEmployeeExceptionHandler(Exception e) {
        return new InvalidEmployeeException(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Exception exceptionHandler(Exception e) {
        return new Exception(e.getMessage());
    }

}
