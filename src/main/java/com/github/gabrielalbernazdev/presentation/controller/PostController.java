package com.github.gabrielalbernazdev.presentation.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.github.gabrielalbernazdev.domain.model.Post;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class PostController implements Serializable {
    private List<Post> posts = new ArrayList<>();
    private List<String> categories = new ArrayList<>();

    @PostConstruct
    public void init() {
        String lorem = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
        posts.add(new Post("Title Post 1", lorem));
        posts.add(new Post("Title Post 2", lorem));
        posts.add(new Post("Title Post 3", lorem));

        categories.add("Tech");
        categories.add("Economy");
        categories.add("Entertainment");
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
