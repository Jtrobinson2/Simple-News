package com.example.simplenews.models;

/*
 * News class objects contain the data fetched from the news api. This is the base data model
 * */
public class News {
    private String imageUrl;
    private String sourceNewsOutlet;
    private String description;
    private String articleUrl;
    private String publishDate;


    /*POJO Constructor*/
    public News(String imageUrl, String sourceNewsOutlet, String description, String articleUrl, String publishDate) {
        this.imageUrl = imageUrl;
        this.sourceNewsOutlet = sourceNewsOutlet;
        this.description = description;
        this.articleUrl = articleUrl;
        this.publishDate = publishDate;
    }

    /*Getters and setters*/
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSourceNewsOutlet() {
        return sourceNewsOutlet;
    }

    public void setSourceNewsOutlet(String sourceNewsOutlet) {
        this.sourceNewsOutlet = sourceNewsOutlet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
}
