package org.gontard.jfx.svg.factories;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageFactory implements Factory {
    @Override
    public Node create(XmlElement el) {
        String url = el.getString("href");
        ImageView imageView = new ImageView(new Image(url));
        imageView.setPreserveRatio(el.getBoolean("preserveAspectRatio", true));
        imageView.setX(el.getDouble("x"));
        imageView.setY(el.getDouble("y"));
        imageView.setFitWidth(el.getDouble("width"));
        imageView.setFitHeight(el.getDouble("height"));
        return imageView;
    }
}