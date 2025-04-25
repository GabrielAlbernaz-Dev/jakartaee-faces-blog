package com.github.gabrielalbernazdev.domain.exception.auth;

import com.github.gabrielalbernazdev.domain.exception.DomainException;

public class InvalidCredentialsException extends DomainException {
    public InvalidCredentialsException() {
        super("The credentials provided does not match.");
    }
}
