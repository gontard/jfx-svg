package org.gontard.jfx.svg.factories;

import javafx.scene.Node;
import javafx.scene.shape.Ellipse;

public class EllipseFactory implements Factory {
    @Override
    public Node create(XmlElement el) {
        Ellipse ellipse = new Ellipse();
        ellipse.setCenterX(el.getDouble("cx"));
        ellipse.setCenterY(el.getDouble("cy"));
        ellipse.setRadiusX(el.getDouble("rx"));
        ellipse.setRadiusY(el.getDouble("ry"));
        return ellipse;
    }
}