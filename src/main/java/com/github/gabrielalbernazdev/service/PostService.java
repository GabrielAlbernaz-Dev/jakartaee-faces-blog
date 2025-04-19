package com.github.gabrielalbernazdev.service;

import java.util.List;

import com.github.gabrielalbernazdev.presentation.dto.PostDTO;
import com.github.gabrielalbernazdev.presentation.dto.UserDTO;

public interface PostService {
    public List<PostDTO> getAllByUser(UserDTO userDTO);
    public List<PostDTO> getAllActiveByUser(UserDTO userDTO);
}
