package org.gontard.jfx.svg.factories;

import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class RectangleFactory implements Factory {
    @Override
    public Node create(XmlElement el) {
        Rectangle rectangle = new Rectangle();
        rectangle.setX(el.getDouble("x"));
        rectangle.setY(el.getDouble("y"));
        rectangle.setWidth(el.getDouble("width"));
        rectangle.setHeight(el.getDouble("height"));
        rectangle.setArcWidth(el.getDouble("rx"));
        rectangle.setArcHeight(el.getDouble("ry"));
        return rectangle;
    }
}
