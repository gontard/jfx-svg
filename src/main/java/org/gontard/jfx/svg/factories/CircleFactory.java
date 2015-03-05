package org.gontard.jfx.svg.factories;

import javafx.scene.shape.Circle;

public class CircleFactory implements Factory {

    @Override
    public Circle create(XmlElement el) {
        Circle circle = new Circle();
        circle.setCenterX(el.getDouble("cx"));
        circle.setCenterY(el.getDouble("cy"));
        circle.setRadius(el.getDouble("r"));
        return circle;
    }
}
