package com.spd.baraholka.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super("Bad Request!");
    }
}
