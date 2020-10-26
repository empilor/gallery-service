package com.vzelenin.microservices.eureka.image.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

//Tell Jackson to detect all  non-public fields, class level solution
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
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
