package com.github.gabrielalbernazdev.domain.vo;

import java.util.Objects;

import jakarta.validation.constraints.NotBlank;

public final class Email {
    @jakarta.validation.constraints.Email
    @NotBlank
    private String address;

    private Email(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public static Email of(String address) {
        return new Email(address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Email email)) return false;
        return Objects.equals(address, email.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @Override
    public String toString() {
        return "Email{" + address + '}';
    }
}
