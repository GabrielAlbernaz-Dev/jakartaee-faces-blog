package com.github.gabrielalbernazdev.service;

import com.github.gabrielalbernazdev.domain.model.User;

public interface AuthService {
    public void login(String username, String password);
    public void register(User user);
}
