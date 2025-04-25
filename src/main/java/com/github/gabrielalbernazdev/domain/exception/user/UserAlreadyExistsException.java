package com.github.gabrielalbernazdev.domain.exception.user;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String identifier) {
        super(String.format("The user with identifier \"%s\" already exists.", identifier));
    }
}
