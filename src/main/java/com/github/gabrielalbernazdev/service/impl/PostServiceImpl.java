package com.github.gabrielalbernazdev.service.impl;

import java.util.List;

import com.github.gabrielalbernazdev.domain.model.Post;
import com.github.gabrielalbernazdev.domain.model.User;
import com.github.gabrielalbernazdev.mapper.PostMapper;
import com.github.gabrielalbernazdev.mapper.UserMapper;
import com.github.gabrielalbernazdev.presentation.dto.PostDTO;
import com.github.gabrielalbernazdev.presentation.dto.UserDTO;
import com.github.gabrielalbernazdev.repository.PostRepository;
import com.github.gabrielalbernazdev.service.PostService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PostServiceImpl implements PostService {
    @Inject
    private PostRepository repository;

    @Override
    public List<PostDTO> getAllByUser(UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        List<Post> posts = repository.findAllByUser(user);
        return PostMapper.toDTOList(posts);
    }

    @Override
    public List<PostDTO> getAllActiveByUser(UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        List<Post> posts = repository.findAllActiveByUser(user);
        return PostMapper.toDTOList(posts);
    }
}
