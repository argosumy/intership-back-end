package com.spd.baraholka.config.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("Not found!");
    }
}
