package com.spd.baraholka.advertisements.exceptions;

public class AdNotFoundException extends RuntimeException {

    public AdNotFoundException(int id) {
        super("Could not find advertisement with id " + id);
    }
}
