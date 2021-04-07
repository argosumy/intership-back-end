package com.spd.baraholka.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("Not found!");
    }
}
