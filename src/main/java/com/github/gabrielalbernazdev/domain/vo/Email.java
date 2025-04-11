package com.github.gabrielalbernazdev.domain.vo;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;

public final class Email {
    @jakarta.validation.constraints.Email
    @NotBlank
    private final String value;

    private Email(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Email of(String value) {
        return new Email(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email)) return false;
        Email email = (Email) o;
        return Objects.equals(value, email.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Email{" + value + '}';
    }
}
