package org.gontard.jfx.svg.factories;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;

public class EllipseFactory extends ShapeFactory {

    public EllipseFactory(PaintFactory paintFactory) {
        super(paintFactory);
    }

    @Override
    protected Shape createShape(XmlElement el) {
        Ellipse ellipse = new Ellipse();
        ellipse.setCenterX(el.getDouble("cx"));
        ellipse.setCenterY(el.getDouble("cy"));
        ellipse.setRadiusX(el.getDouble("rx"));
        ellipse.setRadiusY(el.getDouble("ry"));
        return ellipse;
    }
}