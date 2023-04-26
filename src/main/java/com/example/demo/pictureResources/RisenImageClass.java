package com.example.demo.pictureResources;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;

import java.io.Serial;

public class RisenImageClass extends Div {

    @Serial
    private static final long serialVersionUID = 1L;

    public RisenImageClass(){
        StreamResource imageResource = new StreamResource("logowanie2.png",
                () -> getClass().getResourceAsStream("/logowanie2.png"));

        Image image = new Image(imageResource, "My Streamed Image");
        add(image);
    }


    }

