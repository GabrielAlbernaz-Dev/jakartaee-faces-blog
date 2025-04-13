package com.github.gabrielalbernazdev.session;

import java.io.Serializable;

import com.github.gabrielalbernazdev.domain.model.User;

import jakarta.enterprise.context.SessionScoped;

@SessionScoped
public class UserSession implements Serializable {
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }
    
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }
}
