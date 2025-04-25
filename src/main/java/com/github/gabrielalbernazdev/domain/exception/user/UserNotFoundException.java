package com.github.gabrielalbernazdev.domain.exception.user;

import java.util.UUID;

import com.github.gabrielalbernazdev.domain.exception.DomainException;

public class UserNotFoundException extends DomainException {
    public UserNotFoundException(UUID id) {
        super(id == null 
            ? "User does not exists." 
            : "User with ID " + id + " does not exists."
        );
    }
}
