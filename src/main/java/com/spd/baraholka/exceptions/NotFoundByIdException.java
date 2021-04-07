package com.spd.baraholka.exceptions;

public class NotFoundByIdException extends RuntimeException {

    public NotFoundByIdException(int id) {
        super("Could not find by id: " + id);
    }
}

