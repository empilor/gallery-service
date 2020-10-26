package com.vzelenin.microservices.eureka.image.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

//Tell Jackson to detect all non-public fields, class level solution
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Image {
    private int id;
    private String title;
    private String url;

    public Image(int id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
