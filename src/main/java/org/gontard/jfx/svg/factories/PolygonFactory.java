package org.gontard.jfx.svg.factories;

import javafx.scene.shape.Polygon;

public class PolygonFactory implements Factory {

    @Override
    public Polygon create(XmlElement el) {
        Polygon polygon = new Polygon();
        PolylineFactory.readPoints(el, polygon.getPoints());
        return polygon;
    }

}
