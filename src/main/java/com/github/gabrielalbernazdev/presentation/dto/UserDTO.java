package com.github.gabrielalbernazdev.presentation.dto;

public class UserDTO {
    private String id;
    private String username;

    public UserDTO() {}

    public UserDTO(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
