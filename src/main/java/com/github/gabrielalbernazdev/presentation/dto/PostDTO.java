package com.github.gabrielalbernazdev.presentation.dto;

import java.time.LocalDateTime;

public class PostDTO {
    private String id;
    private String title;
    private String content;
    private String categoryId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PostDTO() {}

    public PostDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public PostDTO(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public PostDTO(String id, String title, String content, String categoryId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "PostDTO [id=" + id + ", title=" + title + ", content=" + content + ", categoryId=" + categoryId
                + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
    }
}