package com.github.gabrielalbernazdev.service.impl;

import java.util.Optional;
import java.util.UUID;

import com.github.gabrielalbernazdev.domain.model.User;
import com.github.gabrielalbernazdev.repository.UserRepository;
import com.github.gabrielalbernazdev.service.AuthService;
import com.github.gabrielalbernazdev.util.PasswordUtil;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AuthServiceImpl implements AuthService {
    @Inject
    private UserRepository userRepository;

    @Override
    public User login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if(!user.isPresent()) {
            throw new RuntimeException("User does not exists");
        }

        if(!PasswordUtil.checkPassword(password, user.get().getPassword())) {
            throw new RuntimeException("Credentials does not match!");
        }

        return user.get();
    }

    @Override
    public UUID register(User user) {
        return userRepository.save(user);
    }
}
