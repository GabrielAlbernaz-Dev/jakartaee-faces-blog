package com.github.gabrielalbernazdev.controller;

import java.io.Serializable;

import com.github.gabrielalbernazdev.domain.model.User;
import com.github.gabrielalbernazdev.service.AuthService;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named  
@ViewScoped
public class AuthController implements Serializable {
    @Inject
    private AuthService service;

    private User user = new User();

    public void register() {
        service.register(user);
    }
}
