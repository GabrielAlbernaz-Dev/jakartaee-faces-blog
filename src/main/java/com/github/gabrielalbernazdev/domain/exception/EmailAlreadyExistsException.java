package com.github.gabrielalbernazdev.domain.exception;

public class EmailAlreadyExistsException extends DomainException {
    public EmailAlreadyExistsException(String email) {
        super(String.format("The email \"%s\" already exists.", email));
    }
}
