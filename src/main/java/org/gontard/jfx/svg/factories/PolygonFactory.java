package org.gontard.jfx.svg.factories;

import javafx.scene.Node;
import javafx.scene.shape.Polygon;

public class PolygonFactory implements Factory {
    @Override
    public Node create(XmlElement el) {
        Polygon polygon = new Polygon();
        PolylineFactory.readPoints(el, polygon.getPoints());
        return polygon;
    }
}
