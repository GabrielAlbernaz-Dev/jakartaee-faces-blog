package com.github.gabrielalbernazdev.mapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.github.gabrielalbernazdev.domain.model.Post;
import com.github.gabrielalbernazdev.presentation.dto.PostDTO;

public class PostMapper {
    private PostMapper() {}

    public static PostDTO toDTO(Post entity) {
        return new PostDTO(
            entity.getId().toString(),
            entity.getTitle(),
            entity.getContent()
        );
    }

    public static Post toEntity(PostDTO dto) {
        return new Post(
            UUID.fromString(dto.getId()),
            dto.getTitle(),
            dto.getContent()
        );
    }

    public static List<PostDTO> toDTOList(List<Post> entities) {
        return entities.stream()
                       .map(PostMapper::toDTO)
                       .collect(Collectors.toList());
    }

    public static List<Post> toEntityList(List<PostDTO> dtos) {
        return dtos.stream()
                   .map(PostMapper::toEntity)
                   .collect(Collectors.toList());
    }
}
