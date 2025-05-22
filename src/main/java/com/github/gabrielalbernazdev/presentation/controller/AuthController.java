package com.github.gabrielalbernazdev.presentation.controller;

import java.io.Serializable;
import java.util.UUID;

import com.github.gabrielalbernazdev.domain.model.User;
import com.github.gabrielalbernazdev.mapper.UserMapper;
import com.github.gabrielalbernazdev.presentation.dto.UserDTO;
import com.github.gabrielalbernazdev.service.AuthService;
import com.github.gabrielalbernazdev.session.UserSession;
import com.github.gabrielalbernazdev.util.ExceptionValidationUtil;

import jakarta.ejb.EJBTransactionRolledbackException;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.ConstraintViolationException;

@Named  
@ViewScoped
public class AuthController implements Serializable {
    @Inject
    private AuthService service;

    @Inject
    private UserSession userSession;

    private UserDTO user = new UserDTO();

    public String login() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        try {
            User userLogin = service.login(user.getUsername(), user.getPassword());
            userSession.setCurrentUser(userLogin);
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
            UUID userId = service.register(user);
            User userEntity = UserMapper.toEntity(user);
            userEntity.setId(userId);
            userSession.setCurrentUser(userEntity);
            facesContext.addMessage(null, new FacesMessage("Register successful!"));
            return "/private/index?faces-redirect=true";
        } catch (ConstraintViolationException  e) {
            ExceptionValidationUtil.handleViolations(e, facesContext);
        } catch(EJBTransactionRolledbackException e) {
            ConstraintViolationException cve = ExceptionValidationUtil.findConstraintViolation(e);
            if (cve != null) {
                ExceptionValidationUtil.handleViolations(cve, facesContext);
            } else {
                facesContext.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error on transaction: " + e.getCause().getMessage(), null));
            }
        } catch (Exception e) {
            facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + e.getMessage(), null));
        }
        return null;
    }

    public String logout() {
        userSession.setCurrentUser(null);
        return "/private/index?faces-redirect=true";
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
