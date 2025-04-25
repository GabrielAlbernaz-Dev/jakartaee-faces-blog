package com.github.gabrielalbernazdev.service;

import java.util.UUID;

import com.github.gabrielalbernazdev.domain.model.User;
import com.github.gabrielalbernazdev.presentation.dto.UserDTO;

public interface AuthService {
    public User login(String username, String password);
    public UUID register(UserDTO userDTO);
}
