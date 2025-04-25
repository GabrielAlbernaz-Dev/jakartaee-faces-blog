package com.github.gabrielalbernazdev.service.impl;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import com.github.gabrielalbernazdev.domain.converter.EmailConverter;
import com.github.gabrielalbernazdev.domain.exception.EmailAlreadyExistsException;
import com.github.gabrielalbernazdev.domain.exception.auth.InvalidCredentialsException;
import com.github.gabrielalbernazdev.domain.exception.user.UserAlreadyExistsException;
import com.github.gabrielalbernazdev.domain.exception.user.UserNotFoundException;
import com.github.gabrielalbernazdev.domain.model.User;
import com.github.gabrielalbernazdev.domain.vo.Email;
import com.github.gabrielalbernazdev.mapper.UserMapper;
import com.github.gabrielalbernazdev.presentation.dto.UserDTO;
import com.github.gabrielalbernazdev.repository.UserRepository;
import com.github.gabrielalbernazdev.service.AuthService;
import com.github.gabrielalbernazdev.util.PasswordUtil;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AuthServiceImpl implements AuthService {
    @Inject
    private UserRepository userRepository;

    private final Logger LOGGER = Logger.getLogger(AuthService.class.getName());

    @Override
    public User login(String username, String password) {
        final Optional<User> user = userRepository.findByUsername(username);
        if(!user.isPresent()) {
            LOGGER.warning("User not found: " + username);
            throw new UserNotFoundException(null);
        }

        if(!PasswordUtil.checkPassword(password, user.get().getPassword())) {
            LOGGER.warning("Invalid credentials for user: " + username);
            throw new InvalidCredentialsException();
        }

        LOGGER.info("User logged in: " + user.get().getUsername());
        return user.get();
    }

    @Override
    public UUID register(UserDTO userDTO) {
        final Optional<User> userByUsername = userRepository.findByUsername(userDTO.getUsername());
        if(userByUsername.isPresent()) {
            LOGGER.warning("User already exists with username: " + userDTO.getUsername());
            throw new UserAlreadyExistsException(userDTO.getUsername());
        }

        final EmailConverter converter = new EmailConverter();
        final Email email = converter.convertToEntityAttribute(userDTO.getEmail());

        final Optional<User> userByEmail = userRepository.findByEmail(email);  
        if(userByEmail.isPresent()) {
            LOGGER.warning("User already exists with email: " + userDTO.getEmail());
            throw new EmailAlreadyExistsException(userDTO.getEmail());
        }

        LOGGER.info("Registering user: " + userDTO.getUsername());
        return userRepository.save(UserMapper.toEntity(userDTO));
    }
}
