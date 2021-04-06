package com.spd.baraholka.config.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super("Bad Request!");
    }
}
