package com.github.gabrielalbernazdev.presentation.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import com.github.gabrielalbernazdev.domain.model.Category;
import com.github.gabrielalbernazdev.domain.model.Post;
import com.github.gabrielalbernazdev.mapper.UserMapper;
import com.github.gabrielalbernazdev.presentation.dto.PostDTO;
import com.github.gabrielalbernazdev.presentation.dto.UserDTO;
import com.github.gabrielalbernazdev.service.CategoryService;
import com.github.gabrielalbernazdev.service.PostService;
import com.github.gabrielalbernazdev.session.UserSession;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class PostController implements Serializable {
    @Inject
    private PostService service;

    @Inject
    private CategoryService categoryService;

    @Inject
    private UserSession userSession;

    private final Logger LOGGER = Logger.getLogger(PostController.class.getName());

    private PostDTO post = new PostDTO();
    private List<PostDTO> posts = new ArrayList<>();
    private List<PostDTO> recentPosts;
    private Category category = new Category();
    private List<Category> categories = new ArrayList<>();

    @PostConstruct
    public void init() {
        UserDTO userDTO = UserMapper.toDTO(userSession.getCurrentUser());
        UUID userUUID = UUID.fromString(userDTO.getId());
        posts = service.getAllByUser(userUUID);
        recentPosts = service.getAllMostRecentByUser(userUUID, 4);
        categories = categoryService.getAll();
    }

    public void save() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UserDTO userDTO = UserMapper.toDTO(userSession.getCurrentUser());
        try {
            if (post.getId() == null) {
                System.out.println("--------------------------" + post + "--------------------------");
                service.create(post);
            } else {
                service.update(post);
            }
        } catch (Exception e) {
            LOGGER.severe("Error: " + e.getMessage());
            facesContext.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + e.getMessage(), null));
        }

        posts = service.getAllActiveByUser(UUID.fromString(userDTO.getId()));
        post = new PostDTO();
    }

    public void delete(PostDTO postDTO) {
        service.delete(UUID.fromString(postDTO.getId()));
        UserDTO userDTO = UserMapper.toDTO(userSession.getCurrentUser());
        posts = service.getAllActiveByUser(UUID.fromString(userDTO.getId()));
    }

    public void newPost() {
        post = new PostDTO();
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

    public List<PostDTO> getRecentPosts() {
        return recentPosts;
    }

    public void setRecentPosts(List<PostDTO> recentPosts) {
        this.recentPosts = recentPosts;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
