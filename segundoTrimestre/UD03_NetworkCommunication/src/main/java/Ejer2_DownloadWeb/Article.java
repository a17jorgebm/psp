package Ejer2_DownloadWeb;

import java.time.LocalDateTime;

public class Article{
    private Integer id;
    private String contentText;
    private Integer userId;
    private String title;
    private String photoUrl;
    private LocalDateTime createdAt;
    private String description;
    private String contentHtml;
    private String category;
    private LocalDateTime updatedAt;

    public Article() {}

    public Article(Integer id, LocalDateTime updatedAt, String category, String contentHtml, String description, LocalDateTime createdAt, String photoUrl, String title, Integer userId, String contentText) {
        this.id = id;
        this.updatedAt = updatedAt;
        this.category = category;
        this.contentHtml = contentHtml;
        this.description = description;
        this.createdAt = createdAt;
        this.photoUrl = photoUrl;
        this.title = title;
        this.userId = userId;
        this.contentText = contentText;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }
}
