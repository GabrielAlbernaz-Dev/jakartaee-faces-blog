package com.github.gabrielalbernazdev.mapper;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.github.gabrielalbernazdev.domain.model.Category;
import com.github.gabrielalbernazdev.domain.model.Post;
import com.github.gabrielalbernazdev.presentation.dto.PostDTO;

public class PostMapper {
    private PostMapper() {}

    public static PostDTO toDTO(Post entity) {
        return new PostDTO(
            entity.getId() != null ? entity.getId().toString() : null,
            entity.getTitle(),
            entity.getContent(),
            entity.getCategory() != null
                ? entity.getCategory().getId().toString()
                : null,
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }

    public static Post toEntity(PostDTO dto) {
        return new Post(
            dto.getId() != null ? UUID.fromString(dto.getId()) : null,
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

    public static void updateEntityFromDTO(PostDTO dto, Post p) {
        if (dto.getTitle()   != null) p.setTitle(dto.getTitle());
        if (dto.getContent() != null) p.setContent(dto.getContent());
        if (dto.getCategoryId() != null) {
            Category cat = new Category();
            cat.setId(UUID.fromString(dto.getCategoryId()));
            p.setCategory(cat);
        }
    }
}
