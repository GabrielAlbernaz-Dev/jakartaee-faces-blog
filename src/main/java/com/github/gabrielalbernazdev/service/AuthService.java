package com.github.gabrielalbernazdev.service;

import java.util.UUID;

import com.github.gabrielalbernazdev.domain.model.User;

public interface AuthService {
    public User login(String username, String password);
    public UUID register(User user);
}
