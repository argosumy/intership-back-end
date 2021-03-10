package com.spd.baraholka.advertisements.persistance;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdvertisementExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AdvertisementStatusNotMatchException.class)
    String advertisementStatusNotMatchHandler(AdvertisementStatusNotMatchException e) {
        return e.getMessage();
    }
}
