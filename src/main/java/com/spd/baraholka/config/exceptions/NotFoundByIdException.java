package com.spd.baraholka.config.exceptions;

public class NotFoundByIdException extends RuntimeException {

    public NotFoundByIdException(int id) {
        super("Could not find by id: " + id);
    }
}

