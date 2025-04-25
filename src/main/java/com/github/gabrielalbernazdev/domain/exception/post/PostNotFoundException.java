package com.github.gabrielalbernazdev.domain.exception.post;

import java.util.UUID;

import com.github.gabrielalbernazdev.domain.exception.DomainException;

public class PostNotFoundException extends DomainException {
    public PostNotFoundException(UUID id) {
        super(id == null 
            ? "Post does not exists." 
            : "Post with ID " + id + " does not exists."
        );
    }
}
