package org.gontard.jfx.svg.factories;

import javafx.scene.Node;
import javafx.scene.shape.Line;

public class LineFactory implements Factory {
    @Override
    public Node create(XmlElement el) {
        Line line = new Line();
        line.setStartX(el.getDouble("x1"));
        line.setStartY(el.getDouble("y1"));
        line.setEndX(el.getDouble("x2"));
        line.setEndY(el.getDouble("y2"));
        return line;
    }
}
