package org.gontard.jfx.svg.factories;

import javafx.scene.shape.Polygon;

public class PolygonFactory extends ShapeFactory {

    public PolygonFactory(PaintFactory paintFactory) {
        super(paintFactory);
    }

    @Override
    protected Polygon createShape(XmlElement el) {
        Polygon polygon = new Polygon();
        PolylineFactory.readPoints(el, polygon.getPoints());
        return polygon;
    }

}
