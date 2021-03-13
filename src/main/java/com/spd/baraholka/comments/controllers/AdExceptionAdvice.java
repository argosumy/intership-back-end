package com.spd.baraholka.comments.controllers;

import com.spd.baraholka.advertisements.exceptions.AdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdExceptionAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(AdNotFoundException.class)
    String adNotFoundHandler(AdNotFoundException e) {
        return e.getMessage();
    }
}
