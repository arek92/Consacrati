package com.example.demo;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import jakarta.annotation.security.PermitAll;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.Serial;

public class ImageClassResourcess extends Div {

    @Serial
    private static final long serialVersionUID = 1L;

    public ImageClassResourcess() {
        StreamResource imageResource = new StreamResource("Zrzut ekranu 2023-04-26 005608.png",
                () -> getClass().getResourceAsStream("/Zrzut ekranu 2023-04-26 005608.png"));

        Image image = new Image(imageResource, "My Streamed Image");
        add(image);
    }
}
