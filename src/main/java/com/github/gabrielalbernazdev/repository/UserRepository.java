package com.github.gabrielalbernazdev.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.github.gabrielalbernazdev.domain.model.User;
import com.github.gabrielalbernazdev.domain.vo.Email;

public interface UserRepository {
    List<User> findAll();
    List<User> findAllActive();
    Optional<User> findById(UUID id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(Email email);
    UUID save(User user);
    void delete(User user);
}
