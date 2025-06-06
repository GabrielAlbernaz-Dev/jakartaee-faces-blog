package com.github.gabrielalbernazdev.domain.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.github.gabrielalbernazdev.domain.converter.EmailConverter;
import com.github.gabrielalbernazdev.domain.vo.Email;
import com.github.gabrielalbernazdev.util.PasswordUtil;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(
        name  = "User.findAll",
        query = "SELECT u FROM User u ORDER BY u.id"
    ),
    @NamedQuery(
        name  = "User.findAllActive",
        query = "SELECT u FROM User u WHERE u.active = true ORDER BY u.id"
    ),
    @NamedQuery(
        name  = "User.findByUsername",
        query = "SELECT u FROM User u WHERE u.username = :username"
    ),
    @NamedQuery(
        name  = "User.findByEmail",
        query = "SELECT u FROM User u WHERE u.email = :email"
    )
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Convert(converter = EmailConverter.class)
    @Column(name = "email")
    @Valid
    private Email email;

    @Column(name = "active")
    private Boolean active;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Post> posts = new HashSet<>();

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        if (password != null && !password.startsWith("$2a$")) {
            this.password = PasswordUtil.encode(password);
        }
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public User() {}

    public User(UUID id, String username, Email email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public User(UUID id, String username, String password, Email email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", active="
                + active + ", posts=" + posts + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
    }
}
