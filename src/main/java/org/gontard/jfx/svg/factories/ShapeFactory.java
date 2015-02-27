package org.gontard.jfx.svg.factories;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public abstract class ShapeFactory implements Factory {
    private final PaintFactory paintFactory;

    public ShapeFactory(PaintFactory paintFactory) {
        this.paintFactory = paintFactory;
    }

    @Override
    public Shape create(XmlElement el) {
        Shape shape = createShape(el);
        shape.setFill(createPaint(el, "fill"));
        shape.setStroke(createPaint(el, "stroke"));
        return shape;
    }

    private Paint createPaint(XmlElement el, String name) {
        return paintFactory.create(el.getString(name));
    }

    protected abstract Shape createShape(XmlElement el);
}
