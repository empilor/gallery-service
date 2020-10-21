package com.vzelenin.microservices.eureka.image.entities;

public class Image {
    private final int id;
    private final String title;
    private final String url;

    public Image(int id, String title, String url) {
        this.id = id;
        this.title = title;
        this.url = url;
    }
}
