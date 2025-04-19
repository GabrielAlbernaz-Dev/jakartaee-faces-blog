package com.github.gabrielalbernazdev.presentation.dto;

public class PostDTO {
    private String id;
    private String title;
    private String content;

    public PostDTO() {}

    public PostDTO(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public PostDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}