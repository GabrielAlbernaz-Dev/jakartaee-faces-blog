package com.github.gabrielalbernazdev.presentation.controller;

import java.io.Serializable;

import com.github.gabrielalbernazdev.domain.model.User;
import com.github.gabrielalbernazdev.service.AuthService;
import com.github.gabrielalbernazdev.session.UserSession;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named  
@ViewScoped
public class AuthController implements Serializable {
    @Inject
    private AuthService service;

    @Inject
    private UserSession userSession;

    private User user = new User();

    public String login() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            service.login(user.getUsername(), user.getPassword());
            userSession.setCurrentUser(user);
            facesContext.addMessage(null, new FacesMessage("Login successful!"));
            return "/private/index?faces-redirect=true";
        } catch (Exception e) {
            facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + e.getMessage(), null));
        }
        return null;
    }

    public String register() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            service.register(user);
            userSession.setCurrentUser(user);
            facesContext.addMessage(null, new FacesMessage("Register successful!"));
            return "/private/index?faces-redirect=true";
        } catch (Exception e) {
            facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + e.getMessage(), null));
        }
        return null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
