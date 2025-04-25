package com.github.gabrielalbernazdev.mapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.github.gabrielalbernazdev.domain.converter.EmailConverter;
import com.github.gabrielalbernazdev.domain.model.User;
import com.github.gabrielalbernazdev.presentation.dto.UserDTO;

public class UserMapper {
    final static EmailConverter emailConverter = new EmailConverter();

    private UserMapper() {}

    public static UserDTO toDTO(User entity) {
        return new UserDTO(
            entity.getId() != null ? entity.getId().toString() : null,
            entity.getUsername(),
            emailConverter.convertToDatabaseColumn(entity.getEmail())
        );
    }

    public static User toEntity(UserDTO dto) {
        return new User(
            dto.getId() != null ? UUID.fromString(dto.getId()) : null,
            dto.getUsername(),
            dto.getPassword(),
            emailConverter.convertToEntityAttribute(dto.getEmail())
        );
    }

    public static List<UserDTO> toDTOList(List<User> entities) {
        return entities.stream()
                       .map(UserMapper::toDTO)
                       .collect(Collectors.toList());
    }

    public static List<User> toEntityList(List<UserDTO> dtos) {
        return dtos.stream()
                   .map(UserMapper::toEntity)
                   .collect(Collectors.toList());
    }
}
