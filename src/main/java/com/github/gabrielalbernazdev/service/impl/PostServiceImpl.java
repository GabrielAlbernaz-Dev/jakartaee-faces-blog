package com.github.gabrielalbernazdev.service.impl;

import java.util.List;
import java.util.UUID;

import com.github.gabrielalbernazdev.domain.model.Post;
import com.github.gabrielalbernazdev.domain.model.User;
import com.github.gabrielalbernazdev.mapper.PostMapper;
import com.github.gabrielalbernazdev.presentation.dto.PostDTO;
import com.github.gabrielalbernazdev.repository.PostRepository;
import com.github.gabrielalbernazdev.service.PostService;
import com.github.gabrielalbernazdev.session.UserSession;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;

@ApplicationScoped
public class PostServiceImpl implements PostService {
    @Inject
    private PostRepository repository;

    @Inject
    private UserSession userSession;

    @Override
    public List<PostDTO> getAllByUser(UUID userId) {
        List<Post> posts = repository.findAllByUser(userId);
        return PostMapper.toDTOList(posts);
    }

    @Override
    public List<PostDTO> getAllActiveByUser(UUID userId) {
        List<Post> posts = repository.findAllActiveByUser(userId);
        return PostMapper.toDTOList(posts);
    }

    @Override
    public void create(PostDTO postDTO) {
        Post post = PostMapper.toEntity(postDTO);
        User currentUser = userSession.getCurrentUser();
        if(currentUser != null) {
            post.setUser(currentUser);
        }

        repository.save(post);
    }

    @Override
    public void update(PostDTO postDTO) {
        Post post = repository
            .findById(UUID.fromString(postDTO.getId()))
            .orElseThrow(() -> new EntityNotFoundException("Post not found"));
            
        PostMapper.updateEntityFromDTO(postDTO, post);
        repository.save(post);
    }

    @Override
    public void delete(UUID id) {
        repository.delete(id);
    }
}
