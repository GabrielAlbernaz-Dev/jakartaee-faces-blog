package com.github.gabrielalbernazdev.domain.converter;

import com.github.gabrielalbernazdev.domain.vo.Email;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class EmailConverter implements AttributeConverter<Email, String> {

    @Override
    public String convertToDatabaseColumn(Email attribute) {
        return attribute != null ? attribute.getValue() : null;
    }

    @Override
    public Email convertToEntityAttribute(String dbData) {
        return dbData != null ? Email.of(dbData) : null;
    }
}

