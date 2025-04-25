package com.github.gabrielalbernazdev.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import com.github.gabrielalbernazdev.domain.exception.post.PostNotFoundException;
import com.github.gabrielalbernazdev.domain.model.Post;
import com.github.gabrielalbernazdev.domain.model.User;
import com.github.gabrielalbernazdev.mapper.PostMapper;
import com.github.gabrielalbernazdev.presentation.dto.PostDTO;
import com.github.gabrielalbernazdev.repository.PostRepository;
import com.github.gabrielalbernazdev.service.PostService;
import com.github.gabrielalbernazdev.session.UserSession;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PostServiceImpl implements PostService {
    @Inject
    private PostRepository repository;

    @Inject
    private UserSession userSession;

    private final Logger LOGGER = Logger.getLogger(PostService.class.getName());

    @Override
    public List<PostDTO> getAllByUser(UUID userId) {
        final List<Post> posts = repository.findAllByUser(userId);
        return PostMapper.toDTOList(posts);
    }

    @Override
    public List<PostDTO> getAllActiveByUser(UUID userId) {
        final List<Post> posts = repository.findAllActiveByUser(userId);
        return PostMapper.toDTOList(posts);
    }

    @Override
    public void create(PostDTO postDTO) {
        final Post post = PostMapper.toEntity(postDTO);
        final User currentUser = userSession.getCurrentUser();
        if(currentUser != null) {
            post.setUser(currentUser);
        }

        repository.save(post);
        LOGGER.warning("Post created with ID: " + post.getId());
    }

    @Override
    public void update(PostDTO postDTO) {
        final UUID postId = UUID.fromString(postDTO.getId());
        final Post post = repository
            .findById(postId)
            .orElseThrow(() -> new PostNotFoundException(postId));
            
        PostMapper.updateEntityFromDTO(postDTO, post);
        repository.save(post);
        LOGGER.warning("Post updated with ID: " + post.getId());
    }

    @Override
    public void delete(UUID id) {
        final Post post = repository
            .findById(id)
            .orElseThrow(() -> new PostNotFoundException(id));

        repository.delete(post);
        LOGGER.warning("Post deleted with ID: " + post.getId());
    }
}
