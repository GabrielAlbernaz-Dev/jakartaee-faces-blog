package com.github.gabrielalbernazdev.presentation.controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class DateController implements Serializable {
    public String getCurrentYear() {
        return String.valueOf(LocalDate.now().getYear());
    }
}
