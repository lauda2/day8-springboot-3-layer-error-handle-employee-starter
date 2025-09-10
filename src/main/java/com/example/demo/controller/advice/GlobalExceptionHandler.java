package com.example.demo.controller.advice;

import com.example.demo.exception.InvalidEmployeeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidEmployeeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public InvalidEmployeeException invalidEmployeeExceptionHandler(Exception e) {
        return new InvalidEmployeeException(e.getMessage());
    }

}
