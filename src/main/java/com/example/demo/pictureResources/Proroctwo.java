package com.example.demo.pictureResources;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.server.StreamResource;

import java.io.Serial;

public class Proroctwo extends Div {

    @Serial
    private static final long serialVersionUID = 1L;

    public Proroctwo(){
        StreamResource imageResource = new StreamResource("proroctwo.png",
                () -> getClass().getResourceAsStream("/proroctwo.png"));

        Image image = new Image(imageResource, "My Streamed Image");
        add(image);
    }
}
