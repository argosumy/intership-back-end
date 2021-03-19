package com.spd.baraholka.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotFoundByIdException.class)
    String notFoundByIdHandler(NotFoundByIdException e) {
        return e.getMessage();
    }
}
