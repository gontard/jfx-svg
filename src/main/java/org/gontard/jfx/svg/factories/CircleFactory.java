package org.gontard.jfx.svg.factories;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CircleFactory extends ShapeFactory {

    public CircleFactory(PaintFactory paintFactory) {
        super(paintFactory);
    }

    @Override
    protected Shape createShape(XmlElement el) {
        Circle circle = new Circle();
        circle.setCenterX(el.getDouble("cx"));
        circle.setCenterY(el.getDouble("cy"));
        circle.setRadius(el.getDouble("r"));
        return circle;
    }
}
