package com.github.gabrielalbernazdev.mapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.github.gabrielalbernazdev.domain.model.User;
import com.github.gabrielalbernazdev.presentation.dto.UserDTO;

public class UserMapper {
    private UserMapper() {}

    public static UserDTO toDTO(User entity) {
        return new UserDTO(
            entity.getId().toString(),
            entity.getUsername()
        );
    }

    public static User toEntity(UserDTO dto) {
        return new User(
            UUID.fromString(dto.getId()),
            dto.getUsername()
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
