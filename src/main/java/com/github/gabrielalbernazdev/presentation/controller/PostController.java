package com.github.gabrielalbernazdev.presentation.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.github.gabrielalbernazdev.domain.model.Post;
import com.github.gabrielalbernazdev.domain.model.User;
import com.github.gabrielalbernazdev.mapper.PostMapper;
import com.github.gabrielalbernazdev.mapper.UserMapper;
import com.github.gabrielalbernazdev.presentation.dto.PostDTO;
import com.github.gabrielalbernazdev.presentation.dto.UserDTO;
import com.github.gabrielalbernazdev.service.PostService;
import com.github.gabrielalbernazdev.session.UserSession;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class PostController implements Serializable {
    @Inject
    private PostService service;

    @Inject
    private UserSession userSession;

    private PostDTO post = new PostDTO();
    private List<PostDTO> posts = new ArrayList<>();
    private List<String> categories = new ArrayList<>();

    @PostConstruct
    public void init() {
        User currentUser = userSession.getCurrentUser();
        System.out.println("------------------------- ");
        System.out.println("Current user: " + currentUser);
        System.out.println("Current user id: " + currentUser.getId());
        System.out.println("------------------------- ");
        if(currentUser != null) {
            UserDTO userDTO = UserMapper.toDTO(userSession.getCurrentUser());
            posts = service.getAllByUser(userDTO);
        }

        categories.add("Tech");
        categories.add("Economy");
        categories.add("Entertainment");
    }

    public void create() {
        
    }

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
