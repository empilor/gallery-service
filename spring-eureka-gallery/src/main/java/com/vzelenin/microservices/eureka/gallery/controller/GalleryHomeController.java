package com.vzelenin.microservices.eureka.gallery.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.vzelenin.microservices.eureka.gallery.entities.Gallery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/")
public class GalleryHomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GalleryHomeController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @RequestMapping("/")
    // This is useful for debugging
    // When having multiple instance of gallery service running at different ports.
    // We load balance among them, and display which instance received the request.
    public String home() {
        return "Gallery Service is running on port: " + env.getProperty("local.server.port");
    }

    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping("/{id}")
    public Gallery getGallery(@PathVariable final int id) {
        LOGGER.info("GalleryHomeController.getGallery() has been called, id = {}", id);
        Gallery gallery = new Gallery(id);
        LOGGER.info("new Gallery has been created");
        List<Object> images = restTemplate.getForObject("http://image-service/images/", List.class);
        gallery.setImages(images);
        LOGGER.info("Images has been set to the new Gallery");

        LOGGER.info("Returning images...");
        return gallery;
    }

    // a fallback method to be called if failure happened (for Hystrix)
    public Gallery fallback(int galleryId, Throwable hystrixCommand) {
        return new Gallery(galleryId);
    }

    // -------- Admin Area --------
    // This method should only be accessed by users with role of 'admin'
    // The logic of role based auth will be added later
    @RequestMapping("/admin")
    public String homeAdmin() {
        return "This is the admin area of Gallery service running at port: " + env.getProperty("local.server.port");
    }
}
