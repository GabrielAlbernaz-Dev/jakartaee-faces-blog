package com.github.gabrielalbernazdev.domain.exception.post;

import com.github.gabrielalbernazdev.domain.exception.DomainException;

public class InvalidPostException extends DomainException {
    public InvalidPostException(String message) { 
        super(message); 
    }
}
