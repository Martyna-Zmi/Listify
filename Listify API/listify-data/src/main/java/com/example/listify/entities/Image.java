package com.example.listify.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String url;
    private int width;
    private int height;

    public long getId() {
        return id;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public String getUrl() {
        return url;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setWidth(int width) {
        this.width = width;
    }
}
