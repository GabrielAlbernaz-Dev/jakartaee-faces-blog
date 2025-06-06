package com.github.gabrielalbernazdev.service;

import java.util.List;
import java.util.UUID;

import com.github.gabrielalbernazdev.presentation.dto.PostDTO;

public interface PostService {
    public List<PostDTO> getAllByUser(UUID userId);
    public List<PostDTO> getAllActiveByUser(UUID userId);
    public List<PostDTO> getAllMostRecentByUser(UUID userId, int limit);
    public void create(PostDTO postDTO);
    public void update(PostDTO postDTO);
    public void delete(UUID id);
}
