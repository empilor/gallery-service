package com.vzelenin.microservices.eureka.image.controller;

import com.vzelenin.microservices.eureka.image.entities.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/")
public class ImageHomeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageHomeController.class);

    @Autowired
    Environment env;

    @RequestMapping("/images")
    public List<Image> getImages() throws Exception {
        //Uncomment next line to test Hystrix fallback method
//        throwException();
        LOGGER.info("Creating collection of Images");
        List<Image> images = Arrays.asList(
                new Image(1, "Treehouse of Horror V", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3842005760"),
                new Image(2, "The Town", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3698134272"),
                new Image(3, "The Last Traction Hero", "https://www.imdb.com/title/tt0096697/mediaviewer/rm1445594112"));
        LOGGER.info("Returning collection of Images...");
        return images;
    }

    private void throwException() throws Exception {
        LOGGER.info("An exception will be throwned. It is within test method to test Hystrix");
        throw new Exception("Images can't be fetched");
    }
}
