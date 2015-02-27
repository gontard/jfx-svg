package org.gontard.jfx.svg.factories;

import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class LineFactory extends ShapeFactory {

    public LineFactory(PaintFactory paintFactory) {
        super(paintFactory);
    }

    @Override
    protected Shape createShape(XmlElement el) {
        Line line = new Line();
        line.setStartX(el.getDouble("x1"));
        line.setStartY(el.getDouble("y1"));
        line.setEndX(el.getDouble("x2"));
        line.setEndY(el.getDouble("y2"));
        return line;
    }

}
