package com.github.gabrielalbernazdev.presentation.converter;

import com.github.gabrielalbernazdev.domain.vo.Email;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(forClass = Email.class)
public class EmailFieldConverter implements Converter<Email> {
    @Override
    public Email getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return Email.of(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Email email) {
        if (email == null) {
            return "";
        }
        return email.getValue();
    }
}
