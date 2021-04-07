package com.spd.baraholka.exceptions;

import org.springframework.security.core.AuthenticationException;

public class OAuth2ProcessingException extends AuthenticationException {
    public OAuth2ProcessingException(String msg, Throwable t) {
        super(msg, t);
    }

    public OAuth2ProcessingException(String msg) {
        super(msg);
    }
}
