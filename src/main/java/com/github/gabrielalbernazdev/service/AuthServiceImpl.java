package com.github.gabrielalbernazdev.service;

import java.util.Optional;

import com.github.gabrielalbernazdev.domain.model.User;
import com.github.gabrielalbernazdev.repository.UserRepository;
import com.github.gabrielalbernazdev.util.PasswordUtil;

import jakarta.inject.Inject;

public class AuthServiceImpl implements AuthService {
    @Inject
    private UserRepository userRepository;

    @Override
    public void login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) {
            throw new RuntimeException("User does not exists");
        }

        if(!PasswordUtil.checkPassword(password, user.get().getPassword())) {
            throw new RuntimeException("Credentials does not match!");
        }
    }

    @Override
    public void register(User user) {
        userRepository.save(user);
    }
}
